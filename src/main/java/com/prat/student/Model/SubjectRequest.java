package com.prat.student.Model;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class SubjectRequest {
    private Integer subjectId;

    private String subjectName;

    private Float maxMark;

    private Float passMark;

    private Integer maxAttempt;

    public SubjectRequest(Integer subjectId, String subjectName, Float maxMark, Float passMark, Integer maxAttempt){
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.maxMark = maxMark;
        this.passMark = passMark;
        this.maxAttempt = maxAttempt;
    }
}
