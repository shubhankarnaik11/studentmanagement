package com.prat.student.Controller;

import com.prat.student.Entity.Subject;
import com.prat.student.Model.SubjectRequest;
import com.prat.student.ServiceImpl.SubjectServiceImpl;
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
    @PostMapping("/create")
    public ResponseEntity<ResponseDataObject> createSubject(@Valid @RequestBody SubjectRequest subject) {
        Subject newSubject = subjectService.createSubject(subject);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.CREATED, newSubject,"Subject Created Successfully", true));
    }

    @Operation(summary = "Get list of all Subjects")
    @GetMapping("/get")
    public ResponseEntity<ResponseDataObject> getAllSubjects() {
        List<Subject> subjectList = subjectService.getAllSubjects();
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, subjectList,"Successful", true));
    }

    @Operation(summary = "Get Subject By Id")
    @GetMapping("/get/{subjectId}")
    public ResponseEntity<ResponseDataObject> getSubject(@PathVariable Integer subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, subject,"Successful", true));
    }


}
