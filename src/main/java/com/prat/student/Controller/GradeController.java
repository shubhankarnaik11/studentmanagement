package com.prat.student.Controller;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Student;
import com.prat.student.Model.GradeRequest;
import com.prat.student.ServiceImpl.GradeServiceImpl;
import com.prat.student.response.ResponseDataObject;
import com.prat.student.response.ResponseObject;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {
    @Autowired
    GradeServiceImpl gradeService;

    @Operation(summary = "Get list of all Grades")
    @GetMapping("/get-all-grades")
    public ResponseEntity<ResponseDataObject> getGrades() {
        List<Grade> classList = gradeService.getGrades();
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.OK, classList,"Successful", true)
        );

    }

    @Operation(summary = "Get Grade By Grade Number")
    @GetMapping("/get-grade-by-grade-no/{gradeNo}")
    public ResponseEntity<ResponseDataObject> getGradeByGradeNo(@PathVariable Integer gradeNo) {
        Grade grade = gradeService.getGradeByGradeNo(gradeNo);
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.OK, grade,"Successful", true)
        );
    }

    @Operation(summary = "Add A Grade")
    @PostMapping("/create-grade")
    public ResponseEntity<ResponseDataObject> createGrade(@RequestBody @Valid GradeRequest grade) {
        gradeService.createGrade(grade);
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.CREATED, null,"Successful", true)
        );
    }

    @Operation(summary = "Add more Subjects to a Grade")
    @PutMapping("/add-subjects-to-grade/{gradeNo}")
    public ResponseEntity<ResponseDataObject> addSubjectsToGrade(@PathVariable Integer gradeNo, @RequestBody List<String> subjects) {
        gradeService.addSubjectsToGrade(gradeNo, subjects);
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.CREATED, null ,"Successful", true)
        );
    }

    @Operation(summary = "Get all students belonging to that grade")
    @GetMapping("/get-grade-students/{gradeNo}")
    public ResponseEntity<ResponseDataObject> getGradeStudents(@PathVariable Integer gradeNo) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.CREATED,  gradeService.getGradeStudents(gradeNo) ,"Successful", true)
        );
    }

    @Operation(summary = "Promote Grade")
    @PostMapping("/promote/{gradeNo}")
    public ResponseEntity<ResponseDataObject> promoteGrade(@PathVariable Integer gradeNo){
        List<HashMap<String, Object>> promotedStudent =  gradeService.promoteAllStudentsByGrade(gradeNo);

        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.CREATED, promotedStudent ,"Successful", true)
        );
    }

    @Operation(summary = "Get Highest marks of the grade")
    @GetMapping("/get-toppers-list/{gradeNo}")
    public ResponseEntity<ResponseDataObject> getToppersList(@PathVariable Integer gradeNo, @RequestParam Integer n){
        Object topper =  gradeService.getNToppers(gradeNo);

        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.CREATED, topper ,"Successful", true)
        );

    }

//    @Operation(summary = "Top Students in a grade")
//    @GetMapping("/top/{gradeNo}/{n}")
//    public ResponseEntity<ResponseDataObject> topNStudents(@PathVariable Integer gradeNo,@PathVariable Integer n){
//        gradeService.getTopNStudents(gradeNo, n);
//
//        return ResponseObject.getResponseObject(
//                new ResponseDataObject(HttpStatus.CREATED, null ,"Successful", true)
//        );
//    }

}
