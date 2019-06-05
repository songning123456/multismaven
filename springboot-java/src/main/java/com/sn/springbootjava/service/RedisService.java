package com.sn.springbootjava.service;

/**
 * @author sn
 */
public interface RedisService {
    void set(String key, Object value);

    void set(String key, Object value, long expireTime);

    boolean exists(final String key);

    <T> T get(String key, Class<T> clazz);

    String get(String key);

    void delete(String key);
}
