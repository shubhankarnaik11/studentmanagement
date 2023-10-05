package com.prat.student.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.config.ValidationsConfig;
import com.prat.student.entity.Subject;
import com.prat.student.validators.ValidatorObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.prat.student.validators.baseValidators.NumericValidator.*;
import static com.prat.student.validators.baseValidators.StringValidator.*;


@Getter
@AllArgsConstructor
public class SubjectDto {


    private String subjectName;

    private Integer maxMark;

    private Integer passMark;

    private Integer maxAttempt;

    public static SubjectDto convertToDto(Subject sub) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(sub, SubjectDto.class);
    }

    public static Subject convertToEntity(SubjectDto sub) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(sub, Subject.class);
    }



}


