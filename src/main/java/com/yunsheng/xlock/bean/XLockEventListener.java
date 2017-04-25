package com.yunsheng.xlock.bean;

import com.yunsheng.xlock.utils.XLockEventEnum;

/**
 * 分布式锁的事件监听器
 *
 * @author yunsheng
 * @since 2016-11-20
 */
public interface XLockEventListener {
    /**
     * 事件
     */
    public XLockEventEnum getEvent();

    /**
     * 触发监听器
     */
    public void fire(Thread thread, XLock xlock);

}
