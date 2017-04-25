package com.yunsheng.xlock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author shengyun
 * Created by shengyun on 17/4/18.
 */
public class Xlock {
    private static final String OK_RESPONSE = "OK";

    private final JedisPool jedisPool;

    private boolean getLock(){
        Jedis resource = null;
        try {
            resource = jedisPool.getResource();
            resource.select(0);

            String response = resource.set("yunsheng", "yunsheng", "NX", "EX", 120L);
            System.out.println(response);

            return isRedieSuccess(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResource(resource);
        }
        return false;
    }

    private boolean isRedieSuccess(String response) {
        return response != null && OK_RESPONSE.equalsIgnoreCase(response);
    }

    public Xlock(String ip, int port) {
        this.jedisPool = new JedisPool(ip, port);
    }

    public static void main(String[] args){
        Xlock xlock = new Xlock("localhost", 6379);
        xlock.getLock();
    }
}
