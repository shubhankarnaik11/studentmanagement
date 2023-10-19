package com.prat.student.kafka.model;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KafkaRequest {
    private String type;
    private Object data;
    private String entityId;
    private String messageId;

}
