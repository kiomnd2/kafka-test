package com.example.kafkatest.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestResponse {

    private String id;

    private String name;

    private LocalDateTime responseDateTime;

    private String errorCode;

    private String errorMessage;
}
