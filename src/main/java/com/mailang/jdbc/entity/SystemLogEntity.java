package com.mailang.jdbc.entity;
import com.mailang.jdbc.persist.annotation.Column;
import com.mailang.jdbc.persist.annotation.Id;
import com.mailang.jdbc.persist.annotation.SequenceGenerator;
import com.mailang.jdbc.persist.annotation.Table;

/**
 * 系统日志记录
 * 调用Logger.x*方法时触发
 *
 * @Author c00241496
 * @Version [OSS Dashboard v1.0]
 * @Date 2019/1/26
 */
@Table(name="TBL_XS_SYSTEM_LOG")
public class SystemLogEntity
{
    @Id
    @SequenceGenerator
    @Column(colName="ID")
    private String id;

    @Column(colName="LEVEL")
    private String level;

    @Column(colName="LAST_UPDATE_TIME")
    private String lastUpdateTime;

    @Column(colName="DETAIL")
    private String detail;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public String getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }
}
