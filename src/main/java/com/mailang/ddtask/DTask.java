package com.mailang.ddtask;

import java.util.List;
import java.util.Map;

public interface DTask
{
    void createTask(List<Map<String, Object>> taskList, Map<String, Object> paramMap);

    void punch(Map<String, Object> data);

}
