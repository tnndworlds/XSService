package com.mailang.jdbc.dao;

import com.mailang.jdbc.entity.JOBEntity;
import com.mailang.jdbc.mybatis.AbstractDao;

public class JOBDao extends AbstractDao<JOBEntity>
{
    public void updateJobStatus(String jobId, int jobStatus)
    {
        JOBEntity entity = new JOBEntity();
        entity.setId(jobId);
        entity.setJobStatus(jobStatus);
        this.update(entity);
    }

    public void updateTaskStatus(String jobId, int taskStatus)
    {
        JOBEntity entity = new JOBEntity();
        entity.setId(jobId);
        entity.setExeStatus(taskStatus);
        this.update(entity);
    }
    public void updateTaskResult(String jobId, int lastResult)
    {
        JOBEntity entity = new JOBEntity();
        entity.setId(jobId);
        entity.setLastResult(lastResult);
        this.update(entity);
    }
}
