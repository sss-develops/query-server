package project.goorm.queryserver.business.web.client.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisCountService implements RedisCountCommand {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisCountService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void increaseCompanyIdCount(Long companyId) {
        redisTemplate.opsForValue().increment(getCompanyIdKey(companyId));
    }

    private String getCompanyIdKey(Long companyId) {
        return String.format("company::search::id::%s", companyId);
    }
}
