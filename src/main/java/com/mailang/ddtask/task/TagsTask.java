package com.mailang.ddtask.task;

import com.mailang.bean.pojo.DTaskBean;
import com.mailang.cons.XSCons;
import com.mailang.ddtask.TaskTypeEnum;
import com.mailang.log.XSLogger;
import com.mailang.tquery.TemplateDataProvider;
import com.mailang.utils.JSONUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public class TagsTask implements DTask
{
    private static String MODULE_ID = "TagsTask";

    private static XSLogger LOG = XSLogger.getLogger(CycleTask.class);

    @Override
    public void createTask(List<Map<String, Object>> taskList, Map<String, Object> paramMap)
    {
        Object retData = TemplateDataProvider.getResult(MODULE_ID, paramMap);
        try
        {
            JSONObject jsonData = JSONObject.fromObject(retData);
            JSONArray dataList = JSONUtils.getArrayByKeyPath(jsonData, "data");
            //数据适配器
            for (int i = 0; i < dataList.size(); i++)
            {
                JSONObject data = dataList.getJSONObject(i);

                //过滤器
                data.put(XSCons.TYPE, TaskTypeEnum.TAGS.getTaskType());
                taskList.add(data);
            }
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public boolean punch(DTaskBean data)
    {
        return true;
    }
}
