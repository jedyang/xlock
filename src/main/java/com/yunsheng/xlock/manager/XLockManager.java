package com.yunsheng.xlock.manager;

import com.yunsheng.xlock.bean.XLock;

/**
 * 分布式锁的管理器
 *
 * @author yunsheng
 */
public interface XLockManager<T extends XLock> {
    /**
     * 创建锁对象。
     */
    public T createLock(String lockName);

    /**
     * 移除锁对象。由使用方自己调用,谨慎使用。
     */
    public boolean removeLock(T lock);

}
