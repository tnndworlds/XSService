package com.mailang.jdbc.dao;


import com.mailang.jdbc.entity.SystemDataEntity;
import com.mailang.jdbc.mybatis.AbstractDao;
import com.mailang.jdbc.mybatis.bean.QCondition;
import com.mailang.jdbc.mybatis.bean.RelateEnum;

import java.util.ArrayList;
import java.util.List;

public class SystemDataDao extends AbstractDao<SystemDataEntity>
{
    public List<SystemDataEntity> getSystemDataByName(String name)
    {
        List<QCondition> conditions = new ArrayList<>();
        QCondition qCondition = new QCondition();
        qCondition.setRelate(RelateEnum.EQUAL);
        qCondition.setAttrName("NAME");
        qCondition.setValue(name);
        conditions.add(qCondition);
        return this.queryRetEntity(conditions);
    }
}
