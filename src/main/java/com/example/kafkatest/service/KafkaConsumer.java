package com.example.kafkatest.service;

import com.example.kafkatest.model.TestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;


@Slf4j
@Component
public class KafkaConsumer {

    @Value("${kafka.consume.topic}")
    private String topic;

    private CountDownLatch latch = new CountDownLatch(1);


    @KafkaListener(topics = "${kafka.consume.topic}", groupId = "${kafka.consume.group}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listener(TestModel testModel) {
        log.debug("test :: {}, {} ", testModel.getId(), testModel.getName());
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
