package com.mailang.taskmgt.plugin;

import com.mailang.jdbc.dao.SystemDataDao;
import com.mailang.jdbc.entity.JOBEntity;
import com.mailang.utils.SpringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostWriteEndTimePlugin implements ParamPlugin
{
    private SystemDataDao systemDataDao = SpringUtils.getBeanByClass(SystemDataDao.class);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    @Override
    public void execute(JOBEntity jobEntity, Map<String, Object> paramMap)
    {
        String params = jobEntity.getExtraParam();
        if (null == params || params.trim().isEmpty())
        {
            return;
        }

        try
        {
            JSONArray paramArray = JSONArray.fromObject(params);
            for (int i = 0; i < paramArray.size(); i++)
            {
                JSONObject param = paramArray.getJSONObject(i);
                if (!param.getBoolean("isSystem"))
                {
                    continue;
                }
                Map<String, Object> saveParam = new HashMap<>();
                saveParam.put("NAME", jobEntity.getId());
                saveParam.put("XS_KEY", param.getString("key"));
                saveParam.put("XS_VALUE", paramMap.get(param.get("dataKey")));
                saveParam.put("LAST_UPDATE_TIME", simpleDateFormat.format(new Date()));
                systemDataDao.saveOrUpdate(saveParam);
            }
        }
        catch (Exception e)
        {
            return;
        }
    }
}
