package com.prat.student.Repository;
import com.prat.student.Entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Integer> {

    public Grade findByGradeNo(Integer gradeNo);

    @Query(value = "Select student_id, sum(mark) as smarks FROM mark WHERE grade_no = :gradeNo and academic_year= :year and is_selected_mark = true group by student_id", nativeQuery = true)
    public List<Object> getToppers(@Param("gradeNo") Integer gradeNo, @Param("year") Integer year);
}
