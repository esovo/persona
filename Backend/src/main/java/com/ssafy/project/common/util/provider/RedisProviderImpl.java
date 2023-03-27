package com.ssafy.project.common.util.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisProviderImpl implements RedisProvider{

    private final RedisTemplate<String, Object> redisTemplate;

    private final RedisTemplate<String, Object> redisBlackListTemplate;

    @Override
    public void set(String key, Object value, long time, TimeUnit timeUnit) {
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(value.getClass()));
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    // 조회되는 값은 object 타입이므로 string으로 형변환
    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void setBlackList(String key, Object value, long time, TimeUnit timeUnit) {

        redisBlackListTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(value.getClass()));
        redisBlackListTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    @Override
    public Object getBlackList(String key) {
        return redisBlackListTemplate.opsForValue().get(key);
    }

    @Override
    public boolean deleteBlackList(String key) {
        return redisBlackListTemplate.delete(key);
    }

    @Override
    public boolean hasKeyBlackList(String key) {
        return redisBlackListTemplate.hasKey(key);
    }

    @Override
    public void cashAuthoriesInDB(Long id, List<String> authories) {

        // 네이버, 카카오, 구글의 registrationId 아이디를 받아 분기해야 하지만, 모두 default aceessToken 유효기간 1시간이므로 final
        final int time = 1;
        final TimeUnit timeUnit = TimeUnit.HOURS;
        String stringId = id.toString();

        redisBlackListTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(String.class));

        for (String auth : authories) {
            redisTemplate.opsForSet().add(stringId, auth);
        }
        
        redisTemplate.expire(stringId, time, timeUnit);
    }
}
