package com.sn.springbootjava.service.impl;

import com.sn.springbootjava.service.RedisService;
import com.sn.springbootjava.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author sn
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 一周有多少秒
     */
    private static final long WEEK_SECONDS = 7 * 24 * 60 * 60;

    /**
     * 将 key，value 存放到redis数据库中，默认设置过期时间为一周
     *
     * @param key
     * @param value
     */
    @Override
    public void set(String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, JsonUtil.convertObject2String(value), WEEK_SECONDS, TimeUnit.SECONDS);
    }

    /**
     * 将 key，value 存放到redis数据库中，设置过期时间单位是秒
     *
     * @param key
     * @param value
     * @param expireTime
     */
    @Override
    public void set(String key, Object value, long expireTime) {
        stringRedisTemplate.opsForValue().set(key, JsonUtil.convertObject2String(value), expireTime, TimeUnit.SECONDS);
    }

    /**
     * 判断 key 是否在 redis 数据库中
     *
     * @param key
     * @return
     */
    @Override
    public boolean exists(final String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 获取与 key 对应的对象
     *
     * @param key
     * @param clazz 目标对象类型
     * @param <T>
     * @return
     */
    @Override
    public <T> T get(String key, Class<T> clazz) {
        String s = get(key);
        if (s == null) {
            return null;
        }
        return JsonUtil.convertString2Object(s, clazz);
    }

    /**
     * 获取 key 对应的字符串
     *
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 删除 key 对应的 value
     *
     * @param key
     */
    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }
}
