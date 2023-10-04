package com.prat.student.repository;

import com.prat.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    public Student findByStudentId(Integer studentId);
}
