package com.mailang.south.data.dataadapter;
import com.mailang.bean.xml.TemplateRest;
import com.mailang.south.data.ParamBean;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @AUTHOR c00241496
 * @create 2017-11-23
 **/
public interface SouthDataAdapterInterface
{
    public List<Map<String, Object>> dataAdapter(JSONObject gotData, ParamBean paramBean);
}
