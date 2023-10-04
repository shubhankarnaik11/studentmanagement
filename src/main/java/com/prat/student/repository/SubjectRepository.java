package com.prat.student.repository;

import com.prat.student.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {

    public Subject findBySubjectId(Integer subjectId);
    public Subject findBySubjectName(String subjectName);

}
