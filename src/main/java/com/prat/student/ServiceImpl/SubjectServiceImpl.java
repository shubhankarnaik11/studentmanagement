package com.prat.student.ServiceImpl;

import com.prat.student.Entity.Subject;
import com.prat.student.Exception.SubjectAlreadyExistsException;
import com.prat.student.Exception.SubjectNotFoundException;
import com.prat.student.Model.SubjectRequest;
import com.prat.student.Repository.SubjectRepository;
import com.prat.student.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepo;


    private boolean checkIfSubjectPresent(String subjectName){
        if(subjectRepo.findBySubjectName(subjectName) == null) return false;
        throw new SubjectAlreadyExistsException();
    }

    @Override
    public Subject createSubject(SubjectRequest subject){
        checkIfSubjectPresent(subject.getSubjectName());

        Subject newSubject = new Subject(
                subject.getSubjectName(),
                subject.getMaxMark(),
                subject.getPassMark(),
                subject.getMaxAttempt()
        );
        subjectRepo.save(newSubject); //try catch here
        return newSubject;

    }

    @Override
    public List<Subject> getAllSubjects(){
        List<Subject> subjectList = new ArrayList<>();
        subjectList = subjectRepo.findAll();
        return subjectList;
    }

    @Override
    public Subject getSubjectById(Integer subjectId){
        Subject subject = subjectRepo.findBySubjectId(subjectId);
        if(subject == null)
            throw new SubjectNotFoundException();
        return subject;
    }


}
