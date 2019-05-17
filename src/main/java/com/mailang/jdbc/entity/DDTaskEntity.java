package com.mailang.jdbc.entity;

import com.mailang.jdbc.persist.annotation.Column;
import com.mailang.jdbc.persist.annotation.Id;
import com.mailang.jdbc.persist.annotation.SequenceGenerator;
import com.mailang.jdbc.persist.annotation.Table;

@Table(name="XS_DD_TASK")
public class DDTaskEntity
{
    @Id
    @Column(colName="ID")
    @SequenceGenerator
    private String ID;

    @Column(colName = "USER")
    private String user;

    @Column(colName = "NAME")
    private String name;

    @Column(colName = "POLICY")
    private Integer policy;

    @Column(colName = "PRIORITY")
    private Integer priority;

    @Column(colName = "POLICY_PARAM")
    private String policyParam;

    @Column(colName = "COUNT")
    private Integer count;

    @Column(colName = "UNIT")
    private String unit;

    @Column(colName = "REMARK")
    private String remark;

    @Column(colName = "DESCRIPTION")
    private String description;

    @Column(colName = "TASK_TYPE")
    private Integer taskType;

    @Column(colName = "TODO")
    private Boolean todo;

    public Integer getTaskType()
    {
        return taskType;
    }

    public void setTaskType(Integer taskType)
    {
        this.taskType = taskType;
    }

    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getPolicy()
    {
        return policy;
    }

    public void setPolicy(Integer policy)
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

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
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
