package com.mailang.datasyn.impl;

import com.huawei.clouddragon.dashboardmgrservice.bean.URLBean;
import com.huawei.clouddragon.dashboardmgrservice.commonsyn.DataSynchronized;
import com.huawei.clouddragon.dashboardmgrservice.utils.SpringUtils;
import net.sf.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RESTData extends DataSynchronized
{
    private static RestTemplate restTemplate = SpringUtils.getBeanByClass(RestTemplate.class);

    @Override
    protected JSONObject getResponse(URLBean urlBean, Map<String, Object> paramMap)
    {
        return null;
    }
}
