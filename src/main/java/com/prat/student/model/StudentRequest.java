package com.prat.student.model;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Data
public class StudentRequest {

    private String studentName;

    private Integer rollNo;

    private String address;

    @Pattern(regexp = "^[1-9][0-9]{9}",message = "Phone number should be of length 10, first digit should not be 0")
    private String contactNumber;

    private String fatherName;

    private String motherName;

    private Integer gradeNo;


}
