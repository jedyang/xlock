package com.yunsheng.xlock.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 工具类
 *
 * @author yunsheng
 * @since 2016-11-16
 */
public class XlockUtil {

    //调度线程池
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    public static ScheduledExecutorService getScheduler() {
        return scheduler;
    }

    /**
     * 当前时间
     */
    public static String nowDatetime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        Date now = new Date();
        return format.format(now);
    }
}
