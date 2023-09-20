package com.prat.student.Model;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class SubjectRequest {
    private Integer subjectId;

    @Column(nullable = false)
    private String subjectName;
}
