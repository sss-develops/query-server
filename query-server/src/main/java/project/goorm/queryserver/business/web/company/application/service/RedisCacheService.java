package project.goorm.queryserver.business.web.company.application.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import project.goorm.queryserver.business.web.company.presentation.response.CompanyResponse;

import java.util.List;

@Component
public class RedisCacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisCacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public List<CompanyResponse> getTopSearchedCompany() {
        return null;
    }

    public void save(List<CompanyResponse> companyResponse) {
        redisTemplate.opsForSet().add("companylist::cache::", companyResponse);
    }
}
