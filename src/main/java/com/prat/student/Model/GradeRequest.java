package com.prat.student.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GradeRequest {

    private Integer gradeNo;

    private List<String> subjects;

}
