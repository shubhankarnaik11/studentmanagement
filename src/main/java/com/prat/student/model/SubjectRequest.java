package com.prat.student.model;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class SubjectRequest {

    @NotBlank(message = "Subject name cannot be blank")
    private String subjectName;

    @NotNull(message = "Max Marks cannot be blank")
    private Float maxMark;

    @NotNull(message = "Pass Marks cannot be blank")
    private Float passMark;

    @Min(1)
    private Integer maxAttempt;

    public SubjectRequest( String subjectName, Float maxMark, Float passMark, Integer maxAttempt){
        this.subjectName = subjectName;
        this.maxMark = maxMark;
        this.passMark = passMark;
        this.maxAttempt = maxAttempt;
    }
}
