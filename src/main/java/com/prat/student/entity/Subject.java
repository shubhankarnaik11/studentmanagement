package com.prat.student.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Subject {

    @Id
    @JsonIgnore
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer subjectId;

    @Column(nullable = false, unique = true)
    private String subjectName;

    @Column(nullable = false)
    @JsonIgnore
    private Integer maxMark;

    @Column(nullable = false)
    @JsonIgnore
    private Integer passMark;

    @Column(nullable = false)
    @JsonIgnore
    private Integer maxAttempt;

    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<Mark> marks;

    @JsonCreator
    public Subject(@JsonProperty("subjectName") String subjectName, @JsonProperty("maxMark") Integer maxMark, @JsonProperty("passMark") Integer passMark, @JsonProperty("maxAttempt") Integer maxAttempt) {
        this.subjectName = subjectName;
        this.maxMark = maxMark;
        this.passMark = passMark;
        this.maxAttempt = maxAttempt;
    }

//    public Subject( String subjectName) {
//        this.subjectName = subjectName;
//    }

}
