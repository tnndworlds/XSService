package com.mailang.ddtask.task;

import com.mailang.ddtask.DTask;
import com.mailang.log.XSLogger;
import com.mailang.tquery.TemplateDataProvider;

import java.util.List;
import java.util.Map;

public class CycleTask implements DTask
{
    private static String MODULE_ID = "";

    private static XSLogger LOG = XSLogger.getLogger(CycleTask.class);

    @Override
    public void createTask(List<Map<String, Object>> taskList, Map<String, Object> paramMap)
    {
        Object retData = TemplateDataProvider.getResult(MODULE_ID, paramMap);
        try
        {

        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void punch(Map<String, Object> data)
    {

    }
}
