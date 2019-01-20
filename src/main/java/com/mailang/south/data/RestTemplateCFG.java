package com.mailang.south.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mailang.bean.xml.TemplateRest;
import com.mailang.cons.XSCons;
import com.mailang.south.data.paramplugin.ParamPluginBean;
import com.mailang.south.data.paramplugin.RequestParamInterface;
import com.mailang.utils.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Load /southdatatemplate/*.xml
 * @AUTHOR c00241496
 * @create 2017-11-23
 **/
public class RestTemplateCFG
{
    private static Map<String, TemplateRest> restTemplateMap = new HashMap<String, TemplateRest>();

    private static Map<String, List<ParamPluginBean>> paramPluginMap = new HashMap<>();

    static
    {
        List<TemplateRest> templateRests = new ArrayList<>();
        File restTemplateCfgDir = new File(FileUtils.getResourcePath("/southdatatemplate"));
        FileUtils.xmlToList(restTemplateCfgDir, templateRests, ".xml", new TypeReference<List<TemplateRest>>() {});
        for (TemplateRest templateRest : templateRests)
        {
            restTemplateMap.put(templateRest.getName(), templateRest);
            String paramPluginRaw = templateRest.getParamPlugin();
            if (StringUtils.isNotBlank(paramPluginRaw))
            {
                String[]paramPlugins = paramPluginRaw.split(XSCons.SEMICOLON);
                List<ParamPluginBean> paramPluginBeans = new ArrayList<>();
                for (String paramPlugin : paramPlugins)
                {
                    String []paramPluginInfo = paramPlugin.split(XSCons.COLON);
                    try
                    {
                        RequestParamInterface requestParamInterface = (RequestParamInterface)Class.forName(paramPluginInfo[0]).newInstance();
                        ParamPluginBean paramPluginBean = new ParamPluginBean();
                        paramPluginBean.setRequestParamInterface(requestParamInterface);
                        paramPluginBean.setPluginParam(paramPluginInfo);
                        paramPluginBeans.add(paramPluginBean);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                paramPluginMap.put(templateRest.getName(), paramPluginBeans);
            }
        }
    }

    public static List<ParamPluginBean> getParamPluginMap(String restName)
    {
        return paramPluginMap.get(restName);
    }

    public static TemplateRest getTemplateRest(String restName)
    {
        return restTemplateMap.get(restName);
    }
}
