package com.prat.student.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.entity.Subject;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class SubjectDto {

    @Pattern(regexp="")
    private String subjectName;

    @Max(100)
    private Float maxMark;

    @Min(35)
    @Max(40)
    private Float passMark;

    @Min(1)
    @Max(3)
    private Integer maxAttempt;

    public SubjectDto( String subjectName, Float maxMark, Float passMark, Integer maxAttempt){
        this.subjectName = subjectName;
        this.maxMark = maxMark;
        this.passMark = passMark;
        this.maxAttempt = maxAttempt;
    }


    public static SubjectDto convertToDto(Subject sub) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(sub, SubjectDto.class);
    }

    public static Subject convertToEntity(SubjectDto sub) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(sub, Subject.class);
    }



}


