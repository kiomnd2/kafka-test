package com.example.kafkatest.service;

import com.example.kafkatest.model.TestModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaProducer {

    private final KafkaTemplate<String, TestModel> kafkaTemplate;

    @Value("${kafka.consume.topic}")
    private String topic;

    public void sendMessage(TestModel message) {
        ListenableFuture<SendResult<String, TestModel>> send = kafkaTemplate.send(topic, message);

        send.addCallback(new ListenableFutureCallback<SendResult<String, TestModel>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, TestModel> result) {
                ProducerRecord<String, TestModel> producerRecord = result.getProducerRecord();
                System.out.println("producerRecord = " + producerRecord.toString());
            }
        });

    }

}
