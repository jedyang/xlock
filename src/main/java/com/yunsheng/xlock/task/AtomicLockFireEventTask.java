package com.yunsheng.xlock.task;

import com.yunsheng.xlock.bean.AtomicLock;
import com.yunsheng.xlock.utils.XLockEventEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 锁,触发某事件
 *
 * @author yunsheng
 * @since 2016-11-20
 */
public class AtomicLockFireEventTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(AtomicLockFireEventTask.class);
    private XLockEventEnum event;
    private Thread thread;
    private AtomicLock atomicLock;

    public AtomicLockFireEventTask(XLockEventEnum event, Thread thread, AtomicLock atomicLock) {
        this.event = event;
        this.thread = thread;
        this.atomicLock = atomicLock;
    }

    public void run() {
        try {
            atomicLock.fireEvent(event, thread);
        } catch (Exception e) {
            logger.error("AtomicLockFireEventTask", e);
        }
    }
}
