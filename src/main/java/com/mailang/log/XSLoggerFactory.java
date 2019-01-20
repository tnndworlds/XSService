package com.mailang.log;

import ch.qos.logback.classic.Logger;

public class XSLoggerFactory
{
    /**
     * 类型日志获取，可用于记录数据到文件
     * @param logName
     * @return
     */
    public static Logger getLogger(String logName)
    {
        return XSRecord.getInstance().getLogger(logName);
    }

    /**
     * 记录系统日志
     * @param clazz
     * @return
     */
    public static XSLogger getLogger(Class clazz)
    {
        return new XSLogger(clazz);
    }
}
