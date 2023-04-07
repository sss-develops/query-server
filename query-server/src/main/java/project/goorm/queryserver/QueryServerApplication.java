package project.goorm.queryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class QueryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryServerApplication.class, args);
    }

}
