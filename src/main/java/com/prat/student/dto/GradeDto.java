package com.prat.student.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.entity.Grade;
import com.prat.student.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
public class GradeDto {

    private Integer gradeNo;

    private List<SubjectDto> subjects;

    @JsonCreator
    public GradeDto(@JsonProperty("gradeNo") Integer gradeNo, @JsonProperty("subjects") List<SubjectDto> subjectDtos){
        this.gradeNo = gradeNo;
        this.subjects = subjectDtos;
    }


    public static GradeDto convertToDto(Grade grade) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(grade, GradeDto.class);
    }

    public static List<GradeDto> convertToDto(List<Grade> grades) {

        List<GradeDto> gradeDtos = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for(Grade g: grades){
            gradeDtos.add(mapper.convertValue(g, GradeDto.class););
        }
        return gradeDtos;

    }

}
