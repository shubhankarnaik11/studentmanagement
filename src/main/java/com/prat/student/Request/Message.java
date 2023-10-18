package com.prat.student.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class Message {

    @JsonProperty("entity")
    String entity;

    @JsonProperty("requestType")
    String requestType;

    @JsonProperty("requestBody")
    Object requestBody;

    @JsonProperty("pathVariable")
    Integer pathVariable;

}
