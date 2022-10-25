package com.example.kafkatest;

import com.example.kafkatest.model.TestModel;
import com.example.kafkatest.service.KafkaConsumer;
import com.example.kafkatest.service.KafkaProducer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;


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
    void testKafka() throws Exception{
        TestModel testModel = new TestModel("123 ","1234");

        producer.sendMessage(testModel);

        boolean await = consumer.getLatch().await(10, TimeUnit.SECONDS);

        Assertions.assertThat(await).isTrue();
    }
}
