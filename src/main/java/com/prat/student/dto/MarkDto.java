package com.prat.student.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.entity.Mark;
import com.prat.student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MarkDto {

    private Integer markId;

    private Float mark;

    public static MarkDto convertToDto(MarkDto mark) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(mark, MarkDto.class);
    }

    public static Mark convertToEntity(Mark mark) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(mark, Mark.class);
    }
}
