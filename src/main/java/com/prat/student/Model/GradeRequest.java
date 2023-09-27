package com.prat.student.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GradeRequest {

    private Integer gradeNo;

    private List<String> subjects;

}
