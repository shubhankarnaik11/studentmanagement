package com.prat.student.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.entity.Student;
import lombok.Getter;

@Getter
public class StudentDto {

    private String studentName;

    private Integer rollNo;

    private String address;

    private Long contactNumber;

    private String fatherName;

    private String motherName;

    private Integer gradeNo;

    @JsonCreator
    public StudentDto(@JsonProperty("studentName") String studentName, @JsonProperty("rollNo") Integer rollNo,
                      @JsonProperty("address") String address, @JsonProperty("contactNumber") Long contactNumber,
                      @JsonProperty("fatherName") String fatherName, @JsonProperty("motherName") String motherName,
                      @JsonProperty("gradeNo") Integer gradeNo) {
        this.studentName = studentName;
        this.rollNo = rollNo;
        this.address = address;
        this.contactNumber = contactNumber;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.gradeNo = gradeNo;
    }

    public static StudentDto convertToDto(Student student) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(student, StudentDto.class);
    }

    public static Student convertToEntity(StudentDto student) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(student, Student.class);
    }


}
