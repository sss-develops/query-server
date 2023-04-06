package project.goorm.queryserver.common.configuration.rdb;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import project.goorm.queryserver.common.configuration.redis.RedisInitialization;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@ContextConfiguration(initializers = DatabaseTestBase.DataSourceInitializer.class)
public abstract class DatabaseTestBase {

    public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Container
        private static final MySQLContainer database = new MySQLContainer("mysql:latest");

        @Container
        static final KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            database.start();
            kafka.start();
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.test.database.replace=none",
                    "spring.datasource.master.jdbc-url=" + database.getJdbcUrl(),
                    "spring.datasource.master.username=" + database.getUsername(),
                    "spring.datasource.master.password=" + database.getPassword(),
                    "spring.datasource.replication.jdbc-url=" + database.getJdbcUrl(),
                    "spring.datasource.replication.username=" + database.getUsername(),
                    "spring.datasource.replication.password=" + database.getPassword(),
                    "spring.kafka.bootstrap-servers", kafka.getBootstrapServers()
            );

        }
    }


    @Autowired
    private RDBInitialization rdbInitialization;

    @Autowired
    private RedisInitialization redisInitialization;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    void setUP() {
        redisInitialization.init();
        rdbInitialization.truncateAllEntity();
    }
}
