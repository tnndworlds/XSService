package com.mailang.south.data.dataadapter;

import com.mailang.bean.xml.TemplateRest;
import com.mailang.south.data.ParamBean;
import com.mailang.utils.FreemarkerAdapter;
import com.mailang.utils.JSONUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR c00241496
 * @create 2017-11-23
 **/
public class ArrayDataAdapter implements SouthDataAdapterInterface
{

    @Override
    public List<Map<String, Object>> dataAdapter(JSONObject responseData, ParamBean paramBean)
    {
        JSONArray dataArray = JSONUtils.getArrayByKeyPath(responseData, paramBean.getTemplateRest().getDataPath());
        if (null == dataArray || dataArray.isEmpty())
        {
            return new ArrayList<>();
        }
        List<Map<String, Object>> retList = new ArrayList<>();
        for (int i = 0; i < dataArray.size(); i++)
        {
            try
            {
                String resultStr = FreemarkerAdapter.getInstance().getResult(paramBean.getTemplateRest().getResponse(), dataArray.getJSONObject(i));
                JSONObject dataObj = JSONObject.fromObject(resultStr);
                retList.add(dataObj);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return retList;
    }
}
