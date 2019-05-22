package com.mailang.ddtask.task;

import com.mailang.bean.pojo.DTaskBean;

import java.util.List;
import java.util.Map;

public interface DTask
{
    void createTask(List<Map<String, Object>> taskList, Map<String, Object> paramMap);

    boolean punch(DTaskBean data);

}
