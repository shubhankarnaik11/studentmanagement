package com.prat.student.serviceimpl;

import com.prat.student.entity.Subject;
import com.prat.student.exception.InvalidPassMarkException;
import com.prat.student.exception.SubjectAlreadyExistsException;
import com.prat.student.exception.SubjectNotFoundException;
import com.prat.student.model.SubjectRequest;
import com.prat.student.repository.SubjectRepository;
import com.prat.student.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepo;


    @Override
    public Subject createSubject(SubjectRequest subject){

        validateSubject(subject);
        Subject newSubject = new Subject(subject.getSubjectName(), subject.getMaxMark(), subject.getPassMark(), subject.getMaxAttempt());
        try{
            newSubject = subjectRepo.save(newSubject);
        }
        catch (Throwable e){
            throw new SubjectAlreadyExistsException("Subject "+ subject.getSubjectName() +" already exists");
        }
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
            throw new SubjectNotFoundException("Subject (ID:" + subjectId + ") not found");
        return subject;
    }

    private void validateSubject(SubjectRequest subject) {
        Integer maxMark = subject.getMaxMark();
        Integer passMark = subject.getPassMark();

        if(passMark > maxMark * 0.35 || passMark < maxMark * 0.30){
            throw new InvalidPassMarkException("Pass mark must be in the range [" + maxMark * 0.30 + "," + maxMark * 0.35 + "]");
        }
    }

}
