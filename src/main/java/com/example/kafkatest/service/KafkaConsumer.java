package com.example.kafkatest.service;

import com.example.kafkatest.model.TestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Slf4j
@Component
public class KafkaConsumer {

    @Value("${kafka.consume.topic}")
    private String topic;

    @KafkaListener(topics = "${kafka.consume.topic}", groupId = "${kafka.consume.group}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listener(TestModel testModel) {
        log.debug("test :: {} ", testModel);
    }

}
