package com.prat.student.controller;

import com.prat.student.entity.Subject;
import com.prat.student.exception.InvalidInputException;
import com.prat.student.model.SubjectRequest;
import com.prat.student.serviceimpl.SubjectServiceImpl;
import com.prat.student.response.ResponseDataObject;
import com.prat.student.response.ResponseObject;
import io.swagger.v3.oas.annotations.Operation;


import jakarta.validation.Valid;
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


    @Operation(summary = "Add new Subject")
    @PostMapping("/")
    public ResponseEntity<ResponseDataObject> createSubject(@Valid @RequestBody SubjectRequest subject) {
        Subject newSubject = subjectService.createSubject(subject);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.CREATED, newSubject,"Subject Created Successfully", true));
    }

    @Operation(summary = "Get list of all Subjects")
    @GetMapping("/")
    public ResponseEntity<ResponseDataObject> getAllSubjects() {
        List<Subject> subjectList = subjectService.getAllSubjects();
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, subjectList ,"Successful", true));
    }

    @Operation(summary = "Get Subject By Id")
    @GetMapping("/{subjectId}")
    public ResponseEntity<ResponseDataObject> getSubject(@PathVariable Integer subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, subject,"Successful", true));
    }


}
