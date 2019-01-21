package com.mailang.taskmgt.plugin;


import com.mailang.jdbc.dao.SystemDataDao;
import com.mailang.jdbc.entity.JOBEntity;
import com.mailang.jdbc.entity.SystemDataEntity;
import com.mailang.utils.SpringUtils;

import java.util.List;
import java.util.Map;

public class PreSystemParamPlugin implements ParamPlugin
{
    private SystemDataDao systemDataDao = SpringUtils.getBeanByClass(SystemDataDao.class);

    @Override
    public void execute(JOBEntity jobEntity, Map<String, Object> paramMap)
    {
        List<SystemDataEntity> systemDataEntities = systemDataDao.getSystemDataByName(jobEntity.getId());
        if (null == systemDataEntities || systemDataEntities.isEmpty())
        {
            return;
        }
        for (SystemDataEntity systemDataEntity : systemDataEntities)
        {
            paramMap.put(systemDataEntity.getXsKey(), systemDataEntity.getXsValue());
        }
    }
}
