package com.example.kafkatest.service;

import com.example.kafkatest.model.TestModel;
import com.example.kafkatest.model.TestResponse;
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

    private final KafkaTemplate<String, TestResponse> kafkaTemplate;

    @Value("${kafka.produce.topic}")
    private String topic;

    public void sendMessage(TestResponse message) throws Exception {
        ListenableFuture<SendResult<String, TestResponse>> send = kafkaTemplate.send(topic, message);

        send.addCallback(new ListenableFutureCallback<SendResult<String, TestResponse>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, TestResponse> result) {
                ProducerRecord<String, TestResponse> producerRecord = result.getProducerRecord();
                System.out.println("producerRecord = " + producerRecord.toString());
            }
        });

    }

}
