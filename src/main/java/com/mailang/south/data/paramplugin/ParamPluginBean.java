package com.mailang.south.data.paramplugin;
/**
 * @AUTHOR c00241496
 * @create 2017-11-25
 **/
public class ParamPluginBean
{
    private RequestParamInterface requestParamInterface;
    private String[] pluginParam;

    public RequestParamInterface getRequestParamInterface()
    {
        return requestParamInterface;
    }

    public void setRequestParamInterface(RequestParamInterface requestParamInterface)
    {
        this.requestParamInterface = requestParamInterface;
    }

    public String[] getPluginParam()
    {
        return pluginParam;
    }

    public void setPluginParam(String[] pluginParam)
    {
        this.pluginParam = pluginParam;
    }
}
