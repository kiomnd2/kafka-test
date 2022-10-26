package com.example.kafkatest;

import com.example.kafkatest.model.TestResponse;
import com.example.kafkatest.listener.KafkaConsumer;
import com.example.kafkatest.service.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;


@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class KafkaTest {

    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private KafkaProducer producer;

    @Value("${kafka.consume.topic}")
    private String topic;

    @Test
    void testKafkaProduceTest() throws Exception{
        TestResponse testModel = new TestResponse("123 ","1234", LocalDateTime.now(), null, null);

        producer.sendMessage(testModel);

    }
}
