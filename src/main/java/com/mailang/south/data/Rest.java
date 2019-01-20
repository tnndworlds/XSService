package com.mailang.south.data;

import com.mailang.bean.xml.TemplateRest;
import com.mailang.cons.XSCons;
import com.mailang.jdbc.mybatis.AbstractDao;
import com.mailang.jdbc.mybatis.SQLDao;
import com.mailang.south.data.dataadapter.AdapterBean;
import com.mailang.south.data.dataadapter.SouthDataAdapterInterface;
import com.mailang.south.data.paramplugin.ParamPluginBean;
import com.mailang.utils.FreemarkerAdapter;
import com.mailang.utils.SpringUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @AUTHOR c00241496
 * @create 2017-11-23
 **/
public abstract class Rest
{
    private static SQLDao sqlDao = SpringUtils.getBeanByClass(SQLDao.class);
    protected static Map<String, ParamBean> requestParamInfo = new HashMap<String, ParamBean>();
    public abstract Object rest(String restName);
    public abstract Object doGet(ParamBean paramBean);
    public abstract Object doPost(ParamBean paramBean);

    protected void prepare(String requestId, String restName)
    {
        requestParamInfo.put(requestId, new ParamBean(requestId, restName));
    }

    protected void setParam(ParamBean paramBean)
    {
        List<ParamPluginBean> paramPluginBeanList = RestTemplateCFG.getParamPluginMap(paramBean.getRestName());
        for (ParamPluginBean paramPluginBean : paramPluginBeanList)
        {
            paramPluginBean.getRequestParamInterface().addParam(paramBean);
        }
    }

    protected HttpHeaders getHeader(ParamBean paramBean)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
        if (StringUtils.isBlank(paramBean.getTemplateRest().getHeader()))
        {
            return httpHeaders;
        }
        String headers = FreemarkerAdapter.getInstance().getResult(paramBean.getTemplateRest().getHeader(), paramBean.getParamMap());
        for (String header : headers.split(XSCons.SEMICOLON))
        {
            String[] keyValue = header.split(XSCons.COLON);
            httpHeaders.add(keyValue[0], keyValue[1]);
        }
        return httpHeaders;
    }

    protected void executeSql(String sqlStr)
    {
        sqlDao.executeSql(sqlStr);
    }

    protected void clean(String requestId)
    {
        requestParamInfo.remove(requestId);
    }

    protected void parseData(ParamBean paramBean, JSONObject responseData)
    {
        SouthDataAdapterInterface southDataAdapterInterface = paramBean.getTemplateRest().getDataAdapter();
        if (null == southDataAdapterInterface)
        {
            return;
        }
        List<Map<String, Object>> parseList = southDataAdapterInterface.dataAdapter(responseData, paramBean);
        paramBean.getAdapterBean().getAllData().addAll(parseList);

        //入库
        if (!paramBean.getTemplateRest().getMaxStorage())
        {
            saveData(parseList, paramBean);
        }
        else if (paramBean.isRequestEnd())
        {
            saveData(paramBean.getAdapterBean().getAllData(), paramBean);
        }
    }

    protected void saveData(List<Map<String, Object>> dataList, ParamBean paramBean)
    {
        AbstractDao abstractDao = (AbstractDao)SpringUtils.getBeanById(paramBean.getTemplateRest().getDaoId());
        for (Map<String, Object> dataMap : paramBean.getAdapterBean().getPageData())
        {
            try
            {
                abstractDao.saveOrUpdate(dataMap, paramBean.getTemplateRest().getPrimaryKey().split(XSCons.COLON));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
