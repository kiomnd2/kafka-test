package com.example.kafkatest.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaErrorHandler {


    @KafkaListener(topics = "${kafka.consume.topic}")
    public void errorListener() {

    }
}
