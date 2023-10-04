package com.prat.student.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer markId;

    @Column(nullable = false)
    private Float mark;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gradeNo")
    private Grade grade;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject;

    @Column(nullable = false)
    private Integer attempt;

    @Column(nullable = false )
    private Integer academicYear;

    private Boolean isSelectedMark;

    public Mark(Float mark, Student student, Subject subject, Grade grade, Integer attempt){
        this.mark = mark;
        this.student = student;
        this.subject = subject;
        this.grade = grade;
        this.attempt = attempt;
        this.academicYear = 2023;
        this.isSelectedMark = false;
    }

}
