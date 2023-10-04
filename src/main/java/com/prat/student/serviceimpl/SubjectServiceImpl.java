package com.prat.student.serviceimpl;

import com.prat.student.dto.SubjectDto;
import com.prat.student.entity.Subject;
import com.prat.student.exception.SubjectAlreadyExistsException;
import com.prat.student.exception.SubjectNotFoundException;
import com.prat.student.repository.SubjectRepository;
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
    public Subject createSubject(SubjectDto subject){

        Subject newSubject = SubjectDto.convertToEntity(subject);

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
