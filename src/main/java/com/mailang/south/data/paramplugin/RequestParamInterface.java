package com.mailang.south.data.paramplugin;

import com.mailang.bean.xml.TemplateRest;
import com.mailang.south.data.ParamBean;

import java.util.Map;

/**
 * @AUTHOR c00241496
 * @create 2017-11-23
 **/
public interface RequestParamInterface
{
    public RequestParamInterface addParam(ParamBean paramBean, String...args);
}
