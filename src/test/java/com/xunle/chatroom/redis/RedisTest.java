package com.xunle.chatroom.redis;


import com.xunle.chatroom.utils.RedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author xunle
 * @date 2022/6/7 21:57
 */


public class RedisTest {

    @Test
    public void testConnect() {
        Jedis jedis = new Jedis("47.98.149.186", 6379);
        jedis.auth("Ww46288700");
        String ping = jedis.ping();
        System.out.println(ping);
        jedis.set("name","xunle");
        jedis.expire("name",100);
        System.out.println(jedis.get("name"));
        RedisUtils utils = new RedisUtils();
    }


}
