package project.goorm.queryserver.test.integration.client.viewcount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import project.goorm.queryserver.business.web.client.redis.RedisCountCommand;
import project.goorm.queryserver.common.configuration.rdb.DatabaseTestBase;
import project.goorm.queryserver.test.integration.IntegrationTestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("레디스 조회 수 증가 테스트")
class RedisViewCountTest extends DatabaseTestBase {


    @Autowired
    private RedisCountCommand redisCountCommand;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    @DisplayName("올바른 회사 아이디가 들어오면 조회수가 1 증가한다.")
    void when_valid_companyid_inputted_then_viewcount_should_be_increased() {
        Long companyId = 100_000L;
        String key = "company::search::id::" + companyId;
        redisTemplate.opsForValue().set(key, 0);

        redisCountCommand.increaseCompanyIdCount(companyId);

        assertEquals(1, redisTemplate.opsForValue().get(key));
    }
}
