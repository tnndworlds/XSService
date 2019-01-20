package com.mailang.utils;

import com.mailang.cons.XSCons;
import com.mailang.jdbc.dao.SYSCfgDao;
import com.mailang.jdbc.entity.SYSCfgEntity;
import com.mailang.jdbc.mybatis.bean.QCondition;
import com.mailang.jdbc.persist.DBUtils;
import com.mailang.log.XSLogger;
import com.mailang.log.XSLoggerFactory;
import com.mailang.xsexception.XSException;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SYSCfg
{
    private static XSLogger LOG = XSLoggerFactory.getLogger(SYSCfg.class);

    private static Map<String, Map<String, SYSCfgEntity>> userCFGMap = new HashedMap();

    private static SYSCfgDao sysCfgDao = null;

    private static boolean initFlag = false;

    static
    {
        try
        {
            sysCfgDao = SpringUtils.getBeanByClass(SYSCfgDao.class);
            List<SYSCfgEntity> cfgList = DBUtils.convertToBean(SYSCfgEntity.class, sysCfgDao.query(new ArrayList<QCondition>()));
            for (SYSCfgEntity sysCfgEntity: cfgList)
            {
                String userId = sysCfgEntity.getUserId();
                Map<String, SYSCfgEntity> tmpMap = userCFGMap.get(userId);
                if (null == tmpMap)
                {
                    tmpMap = new HashMap<String, SYSCfgEntity>();
                    userCFGMap.put(userId, tmpMap);
                }
                tmpMap.put(getKey(sysCfgEntity), sysCfgEntity);
            }
            initFlag = true;
        }
        catch (XSException e)
        {
            LOG.error("XSError. Init system configuration failed. msg: {}.", e.getMessage());
        }
        catch (Exception e1)
        {
            LOG.error("SysError. Init system configuration failed. msg: {}.", e1.getMessage());
        }
    }

    public static String getValue(String key, String module)
    {
        Map<String, SYSCfgEntity> userCfg = userCFGMap.get(XSCons.SYSTEM);
        if (null == userCfg || null == userCfg.get(getKey(XSCons.SYSTEM, module)))
        {
            return "";
        }
        else
        {
            return userCfg.get(getKey(XSCons.SYSTEM, module)).getValue();
        }
    }

    public static String getValue(String userId, String key, String module)
    {
        Map<String, SYSCfgEntity> userCfg = userCFGMap.get(userId);
        if (null == userCfg || null == userCfg.get(getKey(userId, module)))
        {
            return "";
        }
        else
        {
            return userCfg.get(getKey(userId, module)).getValue();
        }
    }

    public static void updateValue(String userId, String module, String key, String value)
    {
        if (StringUtils.isBlank(userId))
        {
            userId = XSCons.SYSTEM;
        }
        Map<String, SYSCfgEntity> userCfg = userCFGMap.get(userId);
        if (null == userCfg)
        {
            userCfg = new HashMap<>();
            userCFGMap.put(userId, userCfg);
        }
        SYSCfgEntity sysCfgEntity = new SYSCfgEntity();
        sysCfgEntity.setUserId(userId);
        sysCfgEntity.setModule(module);
        sysCfgEntity.setKey(key);
        sysCfgEntity.setValue(value);
        userCfg.put(getKey(sysCfgEntity), sysCfgEntity);
        sysCfgDao.save(DBUtils.beanToMap(SYSCfgEntity.class, sysCfgEntity));
    }


    private static String getKey(SYSCfgEntity sysCfgEntity)
    {
        return sysCfgEntity.getUserId() + XSCons.UNDERLINE + sysCfgEntity.getModule();
    }
    private static String getKey(String userId, String module)
    {
        return userId + XSCons.UNDERLINE + module;
    }
}
