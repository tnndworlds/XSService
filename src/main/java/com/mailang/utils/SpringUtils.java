package com.mailang.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtils implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext arg0) throws BeansException
    {
        applicationContext = arg0;
    }

    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    public static Object getBeanById(String id)
    {
        return applicationContext.getBean(id);
    }

    public static <T> T getBeanByClass(Class<T> c)
    {
        return applicationContext.getBean(c);
    }
}