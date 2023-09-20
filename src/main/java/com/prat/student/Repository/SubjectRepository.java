package com.prat.student.Repository;

import com.prat.student.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {

    public Subject findBySubjectName(String subjectName);

}
