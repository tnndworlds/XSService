package com.mailang.south.data;

import com.mailang.bean.xml.TemplateRest;
import com.mailang.south.data.dataadapter.AdapterBean;
import com.mailang.south.data.paramplugin.PageParam;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @AUTHOR c00241496
 * @create 2017-11-25
 **/
public class ParamBean
{
    private String requestId;
    private String restName;
    private boolean requestEnd = false;
    private boolean isPage = false;
    private Integer curPage = 0;
    private TemplateRest templateRest;
    private Map<String, Object> paramMap = new HashMap<String, Object>();
    private AdapterBean adapterBean;

    public ParamBean()
    {

    }

    public ParamBean(String requestId, String restName)
    {
        this.requestId = requestId;
        this.restName = restName;
        this.templateRest = RestTemplateCFG.getTemplateRest(restName);
        this.adapterBean = new AdapterBean();
    }

    public String getRequestId()
    {
        return requestId;
    }

    public void setRequestId(String requestId)
    {
        this.requestId = requestId;
    }

    public String getRestName()
    {
        return restName;
    }

    public void setRestName(String restName)
    {
        this.restName = restName;
    }

    public Map<String, Object> getParamMap()
    {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap)
    {
        this.paramMap = paramMap;
    }

    public boolean isRequestEnd()
    {
        return requestEnd;
    }

    public void setRequestEnd(boolean requestEnd)
    {
        this.requestEnd = requestEnd;
    }

    public boolean isPage()
    {
        return isPage;
    }

    public void setPage(boolean page)
    {
        isPage = page;
    }

    public TemplateRest getTemplateRest()
    {
        return templateRest;
    }

    public void setTemplateRest(TemplateRest templateRest)
    {
        this.templateRest = templateRest;
    }

    public AdapterBean getAdapterBean()
    {
        return adapterBean;
    }

    public void setAdapterBean(AdapterBean adapterBean)
    {
        this.adapterBean = adapterBean;
    }

    public Integer getCurPage()
    {
        return curPage;
    }

    public void setCurPage(Integer curPage)
    {
        this.curPage = curPage;
    }
}
