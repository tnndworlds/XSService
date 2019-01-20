package com.mailang.south.data.impl;

import com.mailang.bean.xml.TemplateRest;
import com.mailang.south.data.ParamBean;
import com.mailang.south.data.Rest;
import com.mailang.south.data.RestTemplateCFG;
import com.mailang.south.data.dataadapter.AdapterBean;
import com.mailang.south.data.paramplugin.PageParam;
import com.mailang.utils.FreemarkerAdapter;
import com.mailang.utils.JSONUtils;
import com.mailang.utils.SpringUtils;
import com.sun.xml.internal.ws.api.server.Adapter;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @AUTHOR c00241496
 * @create 2017-11-23
 **/
public class RestImpl extends Rest
{
    private static Logger LOG = LoggerFactory.getLogger(RestImpl.class);
    private RestTemplate restTemplate = SpringUtils.getBeanByClass(RestTemplate.class);

    @Override
    public Object rest(String restName)
    {
        String requestId = UUID.randomUUID().toString();
        super.prepare(requestId, restName);
        TemplateRest templateRest = RestTemplateCFG.getTemplateRest(restName);
        switch (templateRest.getMethod())
        {
            case "GET":
                doGet(requestParamInfo.get(requestId));
                break;
            case "POST":
                doPost(requestParamInfo.get(requestId));
                break;
            default:
                break;
        }

        super.clean(requestId);
        return null;
    }

    @Override
    public Object doGet(ParamBean paramBean)
    {

        return null;
    }

    @Override
    public Object doPost(ParamBean paramBean)
    {
        TemplateRest templateRest = RestTemplateCFG.getTemplateRest(paramBean.getRestName());
        //1. 准备参数
        super.setParam(paramBean);
        //2. 执行preSql
        super.executeSql(templateRest.getPreSql());
        //3. 数据请求
        HttpHeaders httpHeaders = super.getHeader(paramBean);
        while (!paramBean.isRequestEnd())
        {
            String paramStr = FreemarkerAdapter.getInstance().getResult(templateRest.getRequestParam(), paramBean.getParamMap());
            HttpEntity<JSONObject> formEntity = new HttpEntity<JSONObject>(JSONObject.fromObject(paramStr));
            ResponseEntity<Map> body = null;
            int repeatCount = 0;
            do
            {
                try
                {
                    body = restTemplate.exchange(templateRest.getAddress(), HttpMethod.POST, formEntity, Map.class);
                }
                catch (Exception e)
                {
                    LOG.error("Request failed. Server no response. url : " + templateRest.getAddress());
                }
                repeatCount++;
            }
            while((null == body || body.getStatusCode() != HttpStatus.OK) && repeatCount < 5);

            if (body.getStatusCode() != HttpStatus.OK)
            {
                return null;
            }
            JSONObject responseData = JSONObject.fromObject(body.getBody());

            //Page End
            if (paramBean.isPage())
            {
                int total = Integer.parseInt(String.valueOf(JSONUtils.getValueByKeyPath(responseData, templateRest.getPageTotalKeyPath())));
                int pageSize = Integer.parseInt(paramBean.getTemplateRest().getPageSize());
                if (pageSize * paramBean.getCurPage() > total)
                {
                    paramBean.setRequestEnd(true);
                }
            }

            //Parse Data
            super.parseData(paramBean, responseData);
        }

        super.executeSql(templateRest.getPostSql());
        return null;
    }
}
