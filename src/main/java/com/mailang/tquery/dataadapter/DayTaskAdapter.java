package com.mailang.tquery.dataadapter;

import com.mailang.ddtask.TaskPolicyEnum;
import com.mailang.jdbc.entity.DDTaskEntity;
import com.mailang.jdbc.persist.DBUtils;
import com.mailang.tquery.bean.ContentBean;
import com.mailang.utils.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @Author c00241496
 * @Version [OSS Dashboard v1.0]
 * @Date 2019/1/28
 */
public class DayTaskAdapter implements DataAdapter
{
    @Override
    public Object adapter(ContentBean contentBean, List<Map<String, Object>> dataList)
    {
        List<Map<String, Object>> retList = new ArrayList<>();
        for (Map<String, Object> dataMap : dataList)
        {
            if (showTask(dataMap))
            {
                retList.add(dataMap);
            }
        }
        return retList;
    }

    private boolean showTask(Map<String, Object> dataMap)
    {
        DDTaskEntity ddTaskEntity = DBUtils.getEntity(DDTaskEntity.class, dataMap);
        switch (ddTaskEntity.getTaskType())
        {
            case 1:  //周期任务
                return TaskPolicyEnum.todayIsEffect(String.valueOf(ddTaskEntity.getPolicy()), ddTaskEntity.getPolicyParam());
            case 2:  //目标任务
            case 3:  //快速任务
                return DateTime.isToday(ddTaskEntity.getPolicyParam());
        }
        return false;
    }
}
