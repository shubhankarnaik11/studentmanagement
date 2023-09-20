package com.prat.student.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
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


    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject;

    public Mark(Float mark, Student student, Subject subject){
        this.mark = mark;
        this.student = student;
        this.subject = subject;
    }

}
