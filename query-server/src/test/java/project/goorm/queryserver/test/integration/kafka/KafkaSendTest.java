package project.goorm.queryserver.test.integration.kafka;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.test.context.ActiveProfiles;
import project.goorm.queryserver.common.response.ApiResponse;
import project.goorm.queryserver.common.response.log.ESLog;

import java.util.UUID;

@DisplayName("카프카 Send 테스트")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KafkaSendTest extends KafkaBaseTest{

    @Value("${test.topic}")
    private String topic;

    @DisplayName("문자열을 보내는게 정상작동하는지 테스트한다.")
    @Test
    public void givenKafkaDockerContainer_whenSendingString_thenMessageReceived() {
        MockProducer<String, String> mockProducer = new MockProducer<>(
                true,
                new StringSerializer(),
                new StringSerializer()
        );

        ProducerRecord<String, String> record = new ProducerRecord<>(
                topic,
                "test123"
        );

        mockProducer.send(record);
        Assertions.assertThat(mockProducer.history().size() == 1).isTrue();
    }

    @DisplayName("ESLog클래스를 보내는게 정상적동 하는지 확인한다.")
    @Test
    public void givenKafkaDockerContainer_whenSendingESLog_thenMessageReceived()
            throws Exception {
        MockProducer<String, ESLog> mockProducer = new MockProducer<>(
                true,
                new StringSerializer(),
                new JsonSerializer()
        );

        ApiResponse<String> apiResponse = ApiResponse.of("data");
        ESLog data = new ESLog(
                apiResponse,
                UUID.randomUUID().toString(),
                "sampleMethod",
                5L
        );
        ProducerRecord<String, ESLog> record = new ProducerRecord<>(
                topic,
                data
        );

        mockProducer.send(record);
        Assertions.assertThat(mockProducer.history().size() == 1).isTrue();
    }
}
