package com.prat.student.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Grade {

    @Id
    @Column(nullable = false)
    private Integer gradeNo;


    @ManyToMany(fetch = FetchType.EAGER)
    private List<Subject> subjects;

    @JsonIgnore
    @OneToMany(mappedBy = "currentGrade")
    private List<Student> student;

    public Grade(Integer gradeNo){
        this.gradeNo = gradeNo;
        this.subjects = new ArrayList<Subject>();
    }


    public Grade(Integer gradeNo, List<Subject> subjects){
        this.gradeNo = gradeNo;
        this.subjects = subjects;
    }
}
