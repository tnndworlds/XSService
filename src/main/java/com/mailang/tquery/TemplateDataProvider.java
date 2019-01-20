package com.mailang.tquery;

import com.mailang.cons.ERRCode;
import com.mailang.cons.XSCons;
import com.mailang.jdbc.mybatis.SQLDao;
import com.mailang.tquery.bean.ContentBean;
import com.mailang.tquery.codedata.ICODEData;
import com.mailang.tquery.dataadapter.DataAdapter;
import com.mailang.tquery.uenum.GetTypeEnum;
import com.mailang.utils.FreemarkerAdapter;
import com.mailang.utils.SYSCfg;
import com.mailang.utils.SpringUtils;
import com.mailang.utils.Utils;
import com.mailang.xsexception.XSException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板数据提供实现
 * 作者： chengsongsong
 * 类名：org.sony.utilsTemplateDataProvider
 * 版本：[V01R01C01]
 * 日期：2017年2月5日
 */
public class TemplateDataProvider
{
	private static final Logger LOG = LoggerFactory.getLogger(TemplateDataProvider.class);
	
	private static SQLDao sqlExector = null;
	
	private static Map<String, List<ContentBean>> modulesMap = new HashMap<String, List<ContentBean>>();
	
	private static final String XML_MODULE = "module";
	private static final String XML_NAME = "name";
	private static final String XML_CONTENTS = "contents";
	private static final String XML_CONTENT = "content";
	private static final String XML_KEY = "key";
	private static final String XML_TYPE = "type";
	private static final String XML_VALUE = "value";
	private static final String XML_DATA_ADAPTER = "dataAdapter";
	private static final String XML_SYS_PARAM = "sysParam";
	
	/**
	 * 模板加载
	 * ->能够递归加载
	 * ->仅解析xml
	 */
	static {
		URL templateUrl = TemplateDataProvider.class.getClassLoader().getResource("/template");
		try
		{
			String path = URLDecoder.decode(templateUrl.getPath(), "UTF-8");
			File templateDirFile = new File(path);
			loadTemplateFile(templateDirFile, true);
			LOG.debug("Load Template Success.");
		}
		catch (UnsupportedEncodingException e)
		{
			LOG.error("Init Failed.");
		}
		sqlExector = SpringUtils.getBeanByClass(SQLDao.class);
	}
	
	private static void loadTemplateFile(File templateDirFile, boolean isRecursion)
	{
		if (!templateDirFile.isDirectory())
		{
			LOG.error("Template path incorrect.");
			return;
		}
		File[] allFile = templateDirFile.listFiles();
		for (File tmpFile : allFile)
		{
			if (tmpFile.isDirectory() && isRecursion)
			{
				loadTemplateFile(tmpFile, true);
			}
			else if (tmpFile.getName().endsWith("xml"))
			{
				parseTemplateFile(tmpFile);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void parseTemplateFile(File templateFile)
	{
		SAXReader saxReader = new SAXReader();  
		try
		{
			Document document = saxReader.read(templateFile);
			Element rootElement = document.getRootElement();  
			List<Element> moduleList =  rootElement.elements(XML_MODULE);
			for (Element tmpModule : moduleList)
			{
				String moduleName = Utils.getXMLElementValue(tmpModule.element(XML_NAME));
				List<ContentBean> moduleContentList = new ArrayList<ContentBean>();
				Element contentsEle = tmpModule.element(XML_CONTENTS);
				List<Element> contentList = contentsEle.elements(XML_CONTENT);
				if (null == contentList || contentList.isEmpty())
				{
					continue;
				}
				for (Element conEle : contentList)
				{
					ContentBean tmpConBean = new ContentBean();
					tmpConBean.setKey(Utils.getXMLElementValue(conEle.element(XML_KEY)));
					tmpConBean.setGetType(GetTypeEnum.getType(Utils.getXMLElementValue(conEle.element(XML_TYPE))));
					tmpConBean.setValue(Utils.getXMLElementValue(conEle.element(XML_VALUE)));
					tmpConBean.setDataAdapter(Utils.getXMLElementValue(conEle.element(XML_DATA_ADAPTER)));
					tmpConBean.setSysParam(Utils.getXMLElementValue(conEle.element(XML_SYS_PARAM)));
					moduleContentList.add(tmpConBean);
				}
				modulesMap.put(moduleName, moduleContentList);
			}
			
		}
		catch (Exception e)
		{
			LOG.error("Parse template file failed. fileName: " + templateFile.getName());
			return;
		}
	}
	
	/**
	 * Content Parser
	 * @param moduleName
	 * @param paramMap
	 * @return
	 */
	public static synchronized Object getResult(String moduleName, String userId, Map<String, Object> paramMap)
	{
		List<ContentBean> contentList = modulesMap.get(moduleName);
		if (null == contentList || contentList.isEmpty())
		{
			return "";
		}
		
		JSONObject retObj = new JSONObject();
		for (ContentBean contentBean : contentList)
		{
			retObj.put(contentBean.getKey(), getContentValue(contentBean, userId, paramMap));
		}
		
		return retObj;
	}
	
	private static Object getContentValue(ContentBean contentBean, String userId, Map<String, Object> paramMap)
	{
		switch (contentBean.getGetType())
		{
			case SQL:
				Map<String, Object> allParamMap = new HashMap<String, Object>();
				allParamMap.putAll(paramMap);
				/**
				 * module1:key;module2:key
				 */
				if (StringUtils.isNotBlank(contentBean.getSysParam()))
				{
					String[] keys = contentBean.getSysParam().split(XSCons.SEMICOLON);
					for (String key : keys)
					{
						if (key.split(XSCons.COLON).length != 2)
						{
							continue;
						}
						allParamMap.put(key, SYSCfg.getValue(userId, key.split(XSCons.COLON)[0], key.split(XSCons.COLON)[1]));
					}
				}

				String sqlStr = FreemarkerAdapter.getInstance().getResult(contentBean.getValue(), allParamMap);
				List<Map<String, Object>> queryResult = sqlExector.queryBySql(sqlStr);
				String dataAdapterStr = contentBean.getDataAdapter();
				if (!(null == dataAdapterStr || dataAdapterStr.isEmpty()))
				{
					try
					{
						DataAdapter dataAdapter = (DataAdapter)Class.forName(contentBean.getDataAdapter()).newInstance();
						return dataAdapter.adapter(contentBean, queryResult);
					}
					catch (Exception e)
					{
						throw new XSException(ERRCode.DATA_CONVERT_ERROR, contentBean.getValue(), queryResult);
					}
				}
				return queryResult;
			case DIRECT:
				return contentBean.getValue();
			case JSON:
				try
				{
					return JSONObject.fromObject(contentBean.getValue());
				}
				catch (Exception e)
				{
					throw new XSException(ERRCode.JSON_FORMAT_ERROR, contentBean.getValue());
				}
			case CODE:
				try
				{
					ICODEData codeAdapter = (ICODEData)Class.forName(contentBean.getValue()).newInstance();
					return codeAdapter.query();
				}
				catch (Exception e)
				{
					throw new XSException(ERRCode.CODE_DATA_ERROR, contentBean.getValue());
				}
			default:
				return contentBean.getValue();
		}
	}
}
