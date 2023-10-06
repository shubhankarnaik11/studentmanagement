package com.prat.student.controller;

import com.prat.student.dto.SubjectDto;
import com.prat.student.entity.Subject;
import com.prat.student.exception.InvalidInputException;
import com.prat.student.serviceimpl.SubjectServiceImpl;
import com.prat.student.response.ResponseDataObject;
import com.prat.student.response.ResponseObject;

import com.prat.student.validators.DTOValidators;
import com.prat.student.validators.ValidatorObject;
import io.swagger.v3.oas.annotations.Operation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    SubjectServiceImpl subjectService;

    @Autowired
    DTOValidators dtoValidators;

    @Operation(summary = "Add new Subject")
    @PostMapping("/create-subject")
    public ResponseEntity<ResponseDataObject> createSubject(@Valid @RequestBody SubjectDto subject) {
        Subject newSubject = subjectService.createSubject(subject);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.CREATED, SubjectDto.convertToDto(newSubject),"Subject Created Successfully", true));
    }

    @Operation(summary = "Get list of all Subjects")
    @GetMapping("/get-all-subjects")
    public ResponseEntity<ResponseDataObject> getAllSubjects() {
        List<Subject> subjectList = subjectService.getAllSubjects();
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, SubjectDto.convertToDto(subjectList),"Successful", true));
    }

    @Operation(summary = "Get Subject By Id")
    @GetMapping("/get-subject/{subjectId}")
    public ResponseEntity<ResponseDataObject> getSubject(@PathVariable Integer subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, SubjectDto.convertToDto(subject),"Successful", true));
    }


}
