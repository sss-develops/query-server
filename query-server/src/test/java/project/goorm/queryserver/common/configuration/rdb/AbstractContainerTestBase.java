package project.goorm.queryserver.common.configuration.rdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import project.goorm.queryserver.common.configuration.redis.RedisInitialization;

@SpringBootTest
@ActiveProfiles("test")
public abstract class AbstractContainerTestBase {


    @Autowired
    protected MySQLTestContainer mySQLTestContainer;

    @Autowired
    private RDBInitialization rdbInitialization;

//    @Autowired
//    private RedisInitialization redisInitialization;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    void setUP() {
//        redisInitialization.init();
        rdbInitialization.truncateAllEntity();
    }
}
