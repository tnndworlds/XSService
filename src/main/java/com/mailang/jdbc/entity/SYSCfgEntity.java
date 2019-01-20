package com.mailang.jdbc.entity;

import com.mailang.jdbc.persist.annotation.Column;
import com.mailang.jdbc.persist.annotation.Table;

@Table(name="TBL_XS_SYSTEM_CFG")
public class SYSCfgEntity
{
    @Column(colName="KEY")
    private String key;

    @Column(colName="USER_ID")
    private String userId;

    @Column(colName="MODULE")
    private String module;

    @Column(colName="VALUE")
    private String value;

    @Column(colName="DESCRIPTION")
    private String description;

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getModule()
    {
        return module;
    }

    public void setModule(String module)
    {
        this.module = module;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
