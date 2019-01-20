package com.mailang.utils;

import com.mailang.cons.XSCons;
import com.mailang.tquery.TemplateDataProvider;
import org.dom4j.Element;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class Utils
{
    public static String getXMLElementValue(Element xmlEle)
    {
        return null == xmlEle ? "" : xmlEle.getStringValue();
    }

    public static String getStackTrace(Exception e)
    {
        StringBuffer sb = new StringBuffer();
        if (null != e.toString())
        {
            sb.append("ErrMsg:").append(e.toString()).append(XSCons.WRAP);
        }
        sb.append("StackTrace(More 10):");
        int lines = 0;
        for (StackTraceElement stackTraceElement : e.getStackTrace())
        {
            if (lines > 10)
            {
                break;
            }
            sb.append(stackTraceElement.getClassName())
                    .append(XSCons.SUB_SYMBOL)
                    .append(stackTraceElement.getMethodName())
                    .append(XSCons.COLON)
                    .append(stackTraceElement.getLineNumber())
                    .append(XSCons.WRAP);
            lines++;
        }
        return sb.toString();
    }

    public static String getAppRootPath(){

        try
        {
            URL appRootPath = Utils.class.getClassLoader().getResource("/");
            String path = URLDecoder.decode(appRootPath.getPath(), "UTF-8");
            return path;
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }
}
