package com.prat.student.ServiceImpl;

import com.prat.student.Entity.Subject;
import com.prat.student.Exception.SubjectNotFoundException;
import com.prat.student.Model.SubjectRequest;
import com.prat.student.Repository.SubjectRepository;
import com.prat.student.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepo;

    @Override
    public void createSubject(SubjectRequest subject){
        Subject newSubject = new Subject(subject.getSubjectName(),subject.getMaxMark(),
                subject.getPassMark(),subject.getMaxAttempt());
        subjectRepo.save(newSubject);

    }

    @Override
    public List<Subject> getAllSubjects(){
        List<Subject> subjectList = new ArrayList<>();
        subjectList = subjectRepo.findAll();
        return subjectList;
    }

    @Override
    public Optional<Subject> getSubjectById(Integer subjectId){
        if(subjectRepo.findById(subjectId).isEmpty()){
            throw new SubjectNotFoundException();
        }

        return subjectRepo.findById(subjectId);
    }


}
