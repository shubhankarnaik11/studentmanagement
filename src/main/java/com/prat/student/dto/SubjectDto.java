package com.prat.student.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.entity.Subject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class SubjectDto {

    private String subjectName;

    private Integer maxMark;

    private Integer passMark;

    private Integer maxAttempt;

    @JsonCreator
    public SubjectDto(@JsonProperty("subjectName") String subjectName , @JsonProperty("maxMark") Integer maxMark, @JsonProperty("passMark") Integer passMark, @JsonProperty("maxAttempt") Integer maxAttempt) {
        this.subjectName = subjectName;
        this.maxMark = maxMark;
        this.passMark = passMark;
        this.maxAttempt = maxAttempt;
    }

    public static SubjectDto convertToDto(Subject sub) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(sub, SubjectDto.class);
    }

    public static List<SubjectDto> convertToDto(List<Subject> subjects) {
        List<SubjectDto> subjectDtos = new ArrayList();
        for(Subject s: subjects){
            ObjectMapper mapper = new ObjectMapper();
            subjectDtos.add(mapper.convertValue(s, SubjectDto.class));
        }
        return subjectDtos;


    }

    public static Subject convertToEntity(SubjectDto sub) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(sub, Subject.class);
    }

}


