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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Grade {

    @Id
    @Column(nullable = false)
    private Integer gradeNo;


    @OneToMany(fetch = FetchType.EAGER)
    private List<Subject> subjects;

    @JsonIgnore
    @OneToMany(mappedBy = "gradeNo")
    private List<Student> student;

    public Grade(Integer gradeNo){
        this.gradeNo = gradeNo;
        this.subjects = new ArrayList<Subject>();
    }
}
