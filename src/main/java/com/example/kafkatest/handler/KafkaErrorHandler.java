package com.example.kafkatest.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaErrorHandler implements KafkaListenerErrorHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static String DEAD_TOPIC_NAME = "dev-dead-topic";

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e) {
        return null;
    }

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {

        log.error("[KafkaErrorHandler] kafkaMessage=[" + message.getPayload() + "], errorMessage=[" + exception.getMessage() + "]");


        return KafkaListenerErrorHandler.super.handleError(message, exception, consumer);
    }
}
