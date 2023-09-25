package com.prat.student.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Entity
@NoArgsConstructor
@Getter
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


    public Mark(Float mark, Student student, Subject subject, Grade grade, Integer attempt){
        this.mark = mark;
        this.student = student;
        this.subject = subject;
        this.grade = grade;
        this.attempt = attempt;
        this.academicYear = 2023;
    }

}
