package com.mailang.south.data.paramplugin;
import com.mailang.bean.xml.TemplateRest;
import com.mailang.south.data.ParamBean;
import com.mailang.south.data.RestTemplateCFG;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * @AUTHOR c00241496
 * @create 2017-11-23
 **/
public class PageParam implements RequestParamInterface
{
    @Override
    public RequestParamInterface addParam(ParamBean paramBean, String...args)
    {
        TemplateRest templateRest = RestTemplateCFG.getTemplateRest(paramBean.getRestName());
        String pageNum = getPageNum(args);
        String pageParam = String.valueOf(paramBean.getParamMap().get(pageNum));
        if (StringUtils.isNotBlank(pageParam))
        {
            int curPage = Integer.parseInt(String.valueOf(paramBean.getParamMap().get(pageNum))) + 1;
            paramBean.setCurPage(curPage);
            paramBean.getParamMap().put(pageNum, curPage);
        }
        else
        {
            paramBean.getParamMap().put(pageNum, String.valueOf(templateRest.getStartPageNum()));
            paramBean.setCurPage(Integer.parseInt(String.valueOf(templateRest.getStartPageNum())));
            paramBean.getParamMap().put(getPageSize(args), templateRest.getPageSize());
            paramBean.setPage(true);
        }
        return this;
    }

    public static boolean isEnd(ParamBean paramBean, int total)
    {

        return false;
    }


    public static String getPageNum(String...args)
    {
        for (String param : args)
        {
            if (param.toUpperCase().contains("PAGENUM"))
            {
                return param;
            }
        }
        return null;
    }

    public static String getPageSize(String...args)
    {
        for (String param : args)
        {
            if (param.toUpperCase().contains("PAGESIZE"))
            {
                return param;
            }
        }
        return String.valueOf("100");
    }
}
