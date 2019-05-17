package com.mailang.jdbc.entity;

import com.mailang.jdbc.persist.annotation.Column;
import com.mailang.jdbc.persist.annotation.Id;
import com.mailang.jdbc.persist.annotation.SequenceGenerator;
import com.mailang.jdbc.persist.annotation.Table;

@Table(name="XS_DD_PUNCH_DATA")
public class PunchDataEntity
{
    @Column(colName = "PUNCH_DATE")
    private String punchDate;

    @Column(colName = "USER_ID")
    private String userId;

    @Column(colName = "PUNCH_TYPE")
    private Integer punchType;

    @Column(colName = "TASK_ID")
    private String taskId;

    @Column(colName = "REMARK")
    private String remark;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getPunchDate()
    {
        return punchDate;
    }

    public void setPunchDate(String punchDate)
    {
        this.punchDate = punchDate;
    }

    public Integer getPunchType()
    {
        return punchType;
    }

    public void setPunchType(Integer punchType)
    {
        this.punchType = punchType;
    }

    public String getTaskId()
    {
        return taskId;
    }

    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}
