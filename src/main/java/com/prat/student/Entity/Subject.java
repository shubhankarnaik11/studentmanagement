package com.prat.student.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subject {

    @Id
    @Column(nullable = false)
    private Integer subjectId;

    @Column(nullable = false)
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

    public Subject(Integer subjectId, String subjectName, Float maxMark, Float passMark, Integer maxAttempt){
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.maxMark = maxMark;
        this.passMark = passMark;
        this.maxAttempt = maxAttempt;
    }

}
