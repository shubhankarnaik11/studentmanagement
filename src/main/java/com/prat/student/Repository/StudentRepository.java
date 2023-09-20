package com.prat.student.Repository;

import com.prat.student.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    public Student findByStudentId(Integer studentId);
}
