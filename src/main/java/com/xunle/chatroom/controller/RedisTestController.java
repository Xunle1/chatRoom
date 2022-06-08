package com.xunle.chatroom.controller;

import com.xunle.chatroom.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author xunle
 * @date 2022/6/8 14:03
 */
@RestController
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/{key}/{value}")
    public Response testInsert(@PathVariable String key, @PathVariable String value) {
        redisTemplate.opsForValue().set(key,value,100, TimeUnit.SECONDS);
        return Response.ok().data(key,redisTemplate.opsForValue().get(key));
    }
}
