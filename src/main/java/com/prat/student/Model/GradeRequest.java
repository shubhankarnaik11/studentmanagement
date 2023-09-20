package com.prat.student.Model;

import lombok.Getter;

import java.util.List;

@Getter
public class GradeRequest {

    private Integer currentGrade;

    private List<String> subjects;

}
