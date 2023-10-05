package com.prat.student.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer subjectId;

    @Column(nullable = false, unique = true)
    private String subjectName;

    @Column(nullable = false)
    private Float maxMark;

    @Column(nullable = false)
    private Float passMark;

    @Column(nullable = false)
    private Integer maxAttempt;

    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<Mark> marks;

    public Subject(String subjectName, Float maxMark, Float passMark, Integer maxAttempt){
        this.subjectName = subjectName;
        this.maxMark = maxMark;
        this.passMark = passMark;
        this.maxAttempt = maxAttempt;
    }



}