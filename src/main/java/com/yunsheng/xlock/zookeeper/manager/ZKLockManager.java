package com.yunsheng.xlock.zookeeper.manager;

import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by shengyun on 17/4/26.
 */
public class ZKLockManager {

    private InterProcessMutex interProcessMutex;

    private static final ZKLockManager zkLock = new ZKLockManager();

    private ZKLockManager() {
        String zookeeperConnectionString = "localhost:2181,localhost:2182,localhost:2183";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(
            zookeeperConnectionString, retryPolicy);
        client.start();

        String nodePath = "/test_group";
        interProcessMutex = new InterProcessMutex(client, nodePath);
    }

    public static ZKLockManager getInstance(){
        return zkLock;
    }

    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {

        try {
            return interProcessMutex.acquire(timeout, unit);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean unlock() {
        try {
            interProcessMutex.release();
        } catch (Throwable e) {
            //log.error(e.getMessage(), e);
            e.printStackTrace();
            return false;
        } finally {
            //executorService.schedule(new Cleaner(client, path), delayTimeForClean, TimeUnit.MILLISECONDS);
        }
        return true;
    }

    public InterProcessMutex getInterProcessMutex() {
        return interProcessMutex;
    }

    public void setInterProcessMutex(InterProcessMutex interProcessMutex) {
        this.interProcessMutex = interProcessMutex;
    }


    public static void main(String args[]){

        ZKLockManager zkLock = getInstance();
        try {
            boolean lock = zkLock.tryLock(10, TimeUnit.SECONDS);
            if (lock){
                System.out.println("have the lock");
            }
            boolean unlock = zkLock.unlock();
            if (unlock){
                System.out.println("release the lock");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
