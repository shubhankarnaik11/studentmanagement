package com.prat.student.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.entity.Mark;
import lombok.Getter;

@Getter
public class MarkDto {

    private Integer markId;

    private Float mark;

    @JsonCreator
    public MarkDto(@JsonProperty("markId") Integer markId, @JsonProperty("mark") Float mark) {
        this.markId = markId;
        this.mark = mark;
    }

    public static MarkDto convertToDto(Mark mark) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(mark, MarkDto.class);
    }

    public static Mark convertToEntity(MarkDto mark) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(mark, Mark.class);
    }
}
