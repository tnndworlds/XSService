package com.mailang.jdbc.dao;


import com.mailang.jdbc.entity.SystemLogEntity;
import com.mailang.jdbc.mybatis.AbstractDao;
import com.mailang.utils.DateTime;

import java.util.HashMap;
import java.util.Map;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @Author c00241496
 * @Version [OSS Dashboard v1.0]
 * @Date 2019/1/26
 */
public class SystemLogDao extends AbstractDao<SystemLogEntity>
{
    public void save(String level, String detail)
    {
        Map<String, Object> saveData = new HashMap<>();
        saveData.put("LEVEL", level);
        saveData.put("DETAIL", detail);
        saveData.put("LAST_UPDATE_TIME", DateTime.getCurrentTime());
        this.save(saveData);
    }
}
