package com.mailang.datasyn.condition;

import com.mailang.bean.URLBean;
import net.sf.json.JSONObject;

import java.util.Map;

public interface ConditionPlugin
{
    public boolean isBreak(URLBean urlBean, Map<String, Object> paramMap, JSONObject resData);
}
