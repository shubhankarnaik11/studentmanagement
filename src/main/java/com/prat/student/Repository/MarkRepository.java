package com.prat.student.Repository;

import com.prat.student.Entity.Mark;
import com.prat.student.Entity.Student;
import com.prat.student.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark,Integer> {

    public Mark findBySubjectAndStudent(Subject subject, Student student);

}
