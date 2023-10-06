package com.prat.student.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeRequest {

    @Min(value = 1, message = "Grade No should be at least 1")
    @Max(value = 10, message = "Grade No should be at most 10")
    private Integer gradeNo;

    private List<String> subjects;

}
