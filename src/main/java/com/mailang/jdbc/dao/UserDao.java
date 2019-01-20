package com.mailang.jdbc.dao;

import ch.qos.logback.core.db.dialect.DBUtil;
import com.mailang.jdbc.entity.UserEntity;
import com.mailang.jdbc.mybatis.AbstractDao;
import com.mailang.jdbc.persist.DBUtils;

import java.util.Map;

public class UserDao extends AbstractDao<UserEntity>
{
    public UserEntity validUser(String userId, String password)
    {
        Map<String, Object> dataMap = this.queryById(userId);
        if (null == dataMap)
        {
            return null;
        }
        UserEntity userEntity = DBUtils.getEntity(UserEntity.class, dataMap);
        if (userEntity.getPassword().equals(password))
        {
            return userEntity;
        }
        return null;
    }
}
