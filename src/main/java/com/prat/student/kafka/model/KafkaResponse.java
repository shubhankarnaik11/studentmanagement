package com.prat.student.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaResponse {
    private String messageId;
    private String status;
    private Object data;
    private Map<String, Object> errors = new HashMap<>();
}