package com.prat.student.Repository;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Mark;
import com.prat.student.Entity.Student;
import com.prat.student.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface MarkRepository extends JpaRepository<Mark,Integer> {

    public List<Mark> findBySubjectAndStudentAndGrade(Subject subject, Student student, Grade grade);

}
