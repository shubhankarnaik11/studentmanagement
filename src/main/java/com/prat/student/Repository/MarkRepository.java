package com.prat.student.Repository;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Mark;
import com.prat.student.Entity.Student;
import com.prat.student.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface MarkRepository extends JpaRepository<Mark,Integer> {

    public List<Mark> findBySubjectAndStudentAndGrade(Subject subject, Student student, Grade grade);

    @Query(value = "SELECT * FROM mark WHERE student_id = :id and is_current_year_mark=true", nativeQuery = true)
    public List<Mark> findByStudent(@Param("id") Integer studentId);

    @Modifying
    @Query(value = "DELETE FROM mark WHERE student_id = :studentId and is_current_year_mark=true and subject_id = :subjectId and mark_id <> :markId", nativeQuery = true)
    public void deleteOtherAttempts(@Param("studentId") Integer studentId, @Param("subjectId") Integer subjectId, @Param("markId") Integer markId);

    @Modifying
    @Query(value = "UPDATE MARK SET is_current_year_mark = false WHERE student_id = :studentId", nativeQuery = true)
    public void setCurrentYearFalse(@Param("studentId") Integer studentId);

}
