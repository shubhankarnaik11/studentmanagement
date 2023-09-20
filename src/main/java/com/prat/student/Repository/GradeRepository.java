package com.prat.student.Repository;
import com.prat.student.Entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade,Integer> {
    public Grade findByGradeNo(Integer gradeNo);
}
