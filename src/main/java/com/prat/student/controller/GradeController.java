package com.prat.student.controller;

import com.prat.student.dto.GradeDto;
import com.prat.student.entity.Grade;
import com.prat.student.exception.InvalidInputException;
import com.prat.student.model.GradeRequest;
import com.prat.student.serviceimpl.GradeServiceImpl;
import com.prat.student.response.ResponseDataObject;
import com.prat.student.response.ResponseObject;

import com.prat.student.validators.DTOValidators;
import com.prat.student.validators.ValidatorObject;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {
    @Autowired
    GradeServiceImpl gradeService;

    @Autowired
    DTOValidators dtoValidator;

    @Operation(summary = "Get list of all Grades")
    @GetMapping("/get")
    public ResponseEntity<ResponseDataObject> getGrades() {
        List <Grade> gradeList = gradeService.getGrades();
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, GradeDto.convertToDto(gradeList),"Successful", true));
    }

    @Operation(summary = "Get Grade By Grade Number")
    @GetMapping("/get/{gradeNo}")
    public ResponseEntity<ResponseDataObject> getGradeByGradeNo(@PathVariable Integer gradeNo) {
        Grade grade = gradeService.getGradeByGradeNo(gradeNo);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, GradeDto.convertToDto(grade),"Successful", true));
    }

    @Operation(summary = "Add A Grade")
    @PostMapping("/create")
    public ResponseEntity<ResponseDataObject> createGrade(@RequestBody GradeDto grade) {
        ValidatorObject validObj = dtoValidator.isGradeValid(grade);
        if(!validObj.isSuccess()){
            throw new InvalidInputException(validObj.getErrorMsg());
        }
        Grade newGrade = gradeService.createGrade(grade);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.CREATED, GradeDto.convertToDto(newGrade),"Successful", true));
    }

    @Operation(summary = "Add more Subjects to a Grade")
    @PutMapping("/add/{gradeNo}")
    public ResponseEntity<ResponseDataObject> addSubjectsToGrade(@PathVariable Integer gradeNo, @RequestBody List<String> subjects) {
        gradeService.addSubjectsToGrade(gradeNo, subjects);
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.CREATED, null ,"Successful", true)
        );
    }

    @Operation(summary = "Get all students belonging to that grade")
    @GetMapping("/students/{gradeNo}")
    public ResponseEntity<ResponseDataObject> getGradeStudents(@PathVariable Integer gradeNo) {
        gradeService.getGradeStudents(gradeNo);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.CREATED,  null ,"Successful", true));
    }

    @Operation(summary = "Promote Grade")
    @PostMapping("/promote/{gradeNo}")
    public ResponseEntity<ResponseDataObject> promoteGrade(@PathVariable Integer gradeNo){
        List<HashMap<String, Object>> promotedStudent =  gradeService.promoteAllStudentsByGrade(gradeNo);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, promotedStudent ,"Successful", true));
    }

    @Operation(summary = "Get Highest marks of the grade")
    @GetMapping("/toppers-list/{gradeNo}")
    public ResponseEntity<ResponseDataObject> getToppersList(@PathVariable Integer gradeNo, @RequestParam Integer n){
        List<HashMap <String, Object>> topper =  gradeService.getNToppers(gradeNo, n);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, topper ,"Successful", true));

    }

}
