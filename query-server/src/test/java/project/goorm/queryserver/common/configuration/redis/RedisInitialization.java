package project.goorm.queryserver.common.configuration.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RedisInitialization {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void init() {
        initRedisTemplate();
    }

    private void initRedisTemplate() {
        Set<String> redisKeys = redisTemplate.keys("news::cache::");

        if (redisKeys == null) {
            redisKeys = new HashSet<>();
        }

        for (String key : redisKeys) {
            redisTemplate.delete(key);
        }
    }
}
