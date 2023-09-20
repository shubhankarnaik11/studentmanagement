package com.prat.student.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer studentId;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private Integer rollNo;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Long contactNumber;

    @Column(nullable = false)
    private String fatherName;

    @Column(nullable = false)
    private String motherName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gradeNo")
    private Grade gradeNo;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Mark> marks;

    public Student(Integer studentId, String studentName, Integer rollNo, String address, Long contactNumber, String fatherName, String motherName, Grade gradeNo){
        this.studentId = studentId;
        this.studentName = studentName;
        this.rollNo = rollNo;
        this.address = address;
        this.contactNumber = contactNumber;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.gradeNo = gradeNo;
    }


}
