package com.prat.student.repository;

import com.prat.student.entity.Grade;
import com.prat.student.entity.Mark;
import com.prat.student.entity.Student;
import com.prat.student.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface MarkRepository extends JpaRepository<Mark,Integer> {

    public List<Mark> findBySubjectAndStudentAndGrade(Subject subject, Student student, Grade grade);

    public List<Mark> findByStudentAndAcademicYear(Student student, Integer academicYear);

//    @Modifying
//    @Query(value = "DELETE FROM mark WHERE student_id = :studentId and is_current_year_mark=true and subject_id = :subjectId and mark_id <> :markId", nativeQuery = true)
//    public void deleteOtherAttempts(@Param("studentId") Integer studentId, @Param("subjectId") Integer subjectId, @Param("markId") Integer markId);
//
//    @Modifying
//    @Query(value = "UPDATE MARK SET is_current_year_mark = false WHERE student_id = :studentId", nativeQuery = true)
//    public void setCurrentYearFalse(@Param("studentId") Integer studentId);

}
