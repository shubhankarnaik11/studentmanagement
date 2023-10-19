package com.prat.student.kafka.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class KafkaResponse {
    private String messageId;
    private String status;
    private Object data;
}
