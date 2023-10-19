package com.prat.student.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class SubjectRequest {

    @NotBlank(message = "Subject name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]{3}[a-zA-Z]+", message = "Subject name should be characters and numbers")
    private String subjectName;

    @NotNull(message = "Max Marks cannot be blank")
    @Min(value = 10, message = "Max mark should be at least 10")
    @Max(value = 125, message = "Max Mark should be at most 125")
    private Integer maxMark;

    @NotNull(message = "Pass Marks cannot be blank")
    private Integer passMark;

    @Min(value = 1, message = "Max Attempt should be at least 1")
    @Max(value = 4, message = "Max Attempt should be at most 4")
    private Integer maxAttempt;

    public SubjectRequest( String subjectName, Integer maxMark, Integer passMark, Integer maxAttempt){
        this.subjectName = subjectName;
        this.maxMark = maxMark;
        this.passMark = passMark;
        this.maxAttempt = maxAttempt;
    }
}
