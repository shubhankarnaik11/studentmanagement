package com.prat.student.Model;

import com.prat.student.Entity.Subject;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.List;

@Getter
public class GradeRequest {

    private Integer gradeNo;

    private List<String> subjects;

}
