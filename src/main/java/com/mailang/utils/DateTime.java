package com.mailang.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime
{
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

    public static String getCurrentTime()
    {
        return simpleDateFormat.format(new Date());
    }
}
