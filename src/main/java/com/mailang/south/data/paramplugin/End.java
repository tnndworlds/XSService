package com.mailang.south.data.paramplugin;

import com.mailang.south.data.ParamBean;

/**
 * @AUTHOR c00241496
 * @create 2017-11-25
 **/
public class End implements RequestParamInterface
{
    @Override
    public RequestParamInterface addParam(ParamBean paramBean, String...args)
    {
        paramBean.setRequestEnd(true);
        return this;
    }
}
