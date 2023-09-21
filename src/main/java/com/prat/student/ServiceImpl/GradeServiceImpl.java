package com.prat.student.ServiceImpl;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Subject;
import com.prat.student.Exception.GradeNotFoundException;
import com.prat.student.Exception.SubjectAlreadyExistsException;
import com.prat.student.Exception.SubjectNotFoundException;
import com.prat.student.Model.GradeRequest;
import com.prat.student.Repository.GradeRepository;
import com.prat.student.Repository.SubjectRepository;
import com.prat.student.Service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    GradeRepository gradeRepo;
    @Autowired
    private SubjectRepository subjectRepo;

    private Subject findBySubjectName(String subjectName){
        Subject s = subjectRepo.findBySubjectName(subjectName);
        if( s == null) throw new SubjectNotFoundException();
        return s;
    }

    private Grade findByGradeNo(Integer gradeNo){
        Grade grade = gradeRepo.findByGradeNo(gradeNo);
        if(grade == null) throw new GradeNotFoundException();
        return grade;
    }

    @Override
    public List<Grade> getGrades(){
        return gradeRepo.findAll();
    }

    @Override
    public Grade getGradeByGradeNo(Integer gradeNo){
        return findByGradeNo(gradeNo);
    }

    @Override
    public void createGrade(GradeRequest grade){
        Grade newGrade = new Grade(grade.getGradeNo());
        List<String> subjectList = grade.getSubjects();

        for(String sub : subjectList){
            Subject s = findBySubjectName(sub);
            newGrade.getSubjects().add(subjectRepo.findBySubjectName(sub));
        }
        gradeRepo.save(newGrade);
    }



    @Override
    public void addSubjectsToGrade(Integer gradeNo, List<String> subjects){
        Grade grade = findByGradeNo(gradeNo);

        for(String sub : subjects){
            Subject s = findBySubjectName(sub);
            Optional<Subject> gradeSubject = grade.getSubjects().stream().filter( su -> su.equals(s)).findFirst();
            if(gradeSubject.isPresent()) throw new SubjectAlreadyExistsException();
            grade.getSubjects().add(s);
        }
        gradeRepo.save(grade);
    }
}



