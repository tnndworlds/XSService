package com.mailang.jdbc.entity;


import com.mailang.jdbc.persist.annotation.Column;
import com.mailang.jdbc.persist.annotation.Id;
import com.mailang.jdbc.persist.annotation.Table;

@Table(name="TBL_SYSTEM_DATA")
public class SystemDataEntity
{
    @Id
    @Column(colName="NAME")
    private String name;

    @Id
    @Column(colName="XS_KEY")
    private String xsKey;

    @Column(colName="XS_VALUE")
    private String xsValue;

    @Column(colName="REMARK")
    private String remark;

    @Column(colName="LAST_UPDATE_TIME")
    private String lastUpdateTime;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getXsKey()
    {
        return xsKey;
    }

    public void setXsKey(String xsKey)
    {
        this.xsKey = xsKey;
    }

    public String getXsValue()
    {
        return xsValue;
    }

    public void setXsValue(String xsValue)
    {
        this.xsValue = xsValue;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }
}
