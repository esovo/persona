package com.ssafy.project.common.util.provider;


import java.util.List;
import java.util.concurrent.TimeUnit;

public interface RedisProvider {

    void set(String key, Object value, long time, TimeUnit timeUnit);
    Object get(String key);
    boolean delete(String key);
    boolean hasKey(String key);
    void setBlackList(String key, Object value, long time, TimeUnit timeUnit);
    Object getBlackList(String key);
    boolean deleteBlackList(String key);
    boolean hasKeyBlackList(String key);
    void cashAuthoriesInDB(Long id, List<String> authories);
}

