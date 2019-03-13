package com.mailang.jdbc.entity;

import com.mailang.jdbc.persist.annotation.Column;
import com.mailang.jdbc.persist.annotation.Id;
import com.mailang.jdbc.persist.annotation.SequenceGenerator;
import com.mailang.jdbc.persist.annotation.Table;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @Author c00241496
 * @Version [OSS Dashboard v1.0]
 * @Date 2019/1/28
 */
@Table(name="TBL_XS_SYSTEM_LOG")
public class DDTaskEntity
{
    @Id
    @SequenceGenerator
    @Column(colName="ID")
    private String id;

    @Column(colName="USER")
    private String user;

    @Column(colName="TYPE")
    private Integer type;

    @Column(colName="NAME")
    private String name;

    @Column(colName="TAGS")
    private String tags;

    @Column(colName="POLICY")
    private String policy;

    @Column(colName="PRIORITY")
    private Integer priority;

    @Column(colName="POLICY_PARAM")
    private String policyParam;

    @Column(colName="COUNT")
    private Integer count;

    @Column(colName="UNIT")
    private String unit;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTags()
    {
        return tags;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public String getPolicy()
    {
        return policy;
    }

    public void setPolicy(String policy)
    {
        this.policy = policy;
    }

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public String getPolicyParam()
    {
        return policyParam;
    }

    public void setPolicyParam(String policyParam)
    {
        this.policyParam = policyParam;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }
}
