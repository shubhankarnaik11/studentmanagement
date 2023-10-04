package com.prat.student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentRequest {

    private String studentName;

    private Integer rollNo;

    private String address;

    private Long contactNumber;

    private String fatherName;

    private String motherName;

    private Integer gradeNo;


}
