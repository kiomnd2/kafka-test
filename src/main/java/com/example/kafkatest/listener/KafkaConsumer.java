package com.example.kafkatest.listener;

import com.example.kafkatest.model.TestModel;
import com.example.kafkatest.model.TestResponse;
import com.example.kafkatest.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;


@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumer {


    private CountDownLatch latch = new CountDownLatch(1);

    private final KafkaProducer producer;


    @KafkaListener(topics = "${kafka.consume.topic}", groupId = "${kafka.consume.group}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listener(@Headers MessageHeaders headers, @Payload TestModel testModel) {
        log.debug("test :: {}, {} ", testModel.getId(), testModel.getName());
        log.debug("header :: {}", headers);

        try {
            TestResponse response = new TestResponse();
            response.setId(testModel.getId());
            response.setName(testModel.getName());
            response.setResponseDateTime(LocalDateTime.now());
            producer.sendMessage(response);
        } catch (Exception e) {

        }
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
