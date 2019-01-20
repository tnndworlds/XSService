package com.mailang;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.StringWriter;

/**
 * 常用JSON操作方法
 *
 * @Description: [json操作类]
 * @Author:      [c00241496]
 * @Date:        [2017/9/20]
 * @Version:     [iManagerU2000V200R018C10]
 */
public class JsonUtil
{

    /**
     * xml转json对象
     * @param xmlPath xml文件路径
     * @return json 对象
     */
    public static JSONObject xmlToJson(String xmlPath)
    {
        StringWriter sw = new StringWriter();
        try
        {
            JsonParser jp = new XmlMapper().getFactory().createParser(new File(xmlPath));
            JsonGenerator jg = new ObjectMapper().getFactory().createGenerator(sw);
            while(jp.nextToken() != null){
                jg.copyCurrentEvent(jp);
            }
            jp.close();
            jg.close();
            return JSONObject.fromObject(sw.toString());
        }
        catch (Exception e)
        {
            return null;
        }
    }

  public static void main(String[] args){
        System.out.println(xmlToJson("D:/TelecomI1Config.xml").toString());
  }
}
