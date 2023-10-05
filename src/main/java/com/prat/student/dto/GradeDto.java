package com.prat.student.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.List;

@Getter
@AllArgsConstructor
public class GradeDto {

    private Integer gradeNo;

    private List<String> subjects;

    public static GradeDto convertToDto(Grade grade) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(grade, GradeDto.class);
    }

    public static Grade convertToEntity(GradeDto grade) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(grade, Grade.class);
    }

}
