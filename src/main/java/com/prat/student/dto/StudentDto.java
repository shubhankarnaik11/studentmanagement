package com.prat.student.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentDto {

    private String studentName;

    private Integer rollNo;

    private String address;

    private Long contactNumber;

    private String fatherName;

    private String motherName;

    private Integer gradeNo;

    public static StudentDto convertToDto(Student student) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(student, StudentDto.class);
    }

    public static Student convertToEntity(StudentDto student) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(student, Student.class);
    }


}
