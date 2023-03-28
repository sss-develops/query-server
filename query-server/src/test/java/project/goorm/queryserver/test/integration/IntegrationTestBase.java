package project.goorm.queryserver.test.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import project.goorm.queryserver.common.configuration.rdb.RDBInitialization;
import project.goorm.queryserver.common.configuration.redis.RedisInitialization;

@SpringBootTest
@ActiveProfiles("test")
public abstract class IntegrationTestBase {

    @Autowired
    private RDBInitialization rdbInitialization;

    @Autowired
    private RedisInitialization redisInitialization;

    @Autowired
    protected ObjectMapper objectMapper;

    @AfterEach
    void setUP() {
        redisInitialization.init();
        rdbInitialization.truncateAllEntity();
    }
}
