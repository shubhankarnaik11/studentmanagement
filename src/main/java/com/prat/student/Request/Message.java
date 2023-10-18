package com.prat.student.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class Message {

    @JsonProperty("entity")
    String entity;

    @JsonProperty("type")
    String type;

    @JsonProperty("data")
    Object data;

}
