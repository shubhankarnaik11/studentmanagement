package com.prat.student.Controller;

import com.prat.student.Entity.Grade;
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

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {
    @Autowired
    GradeServiceImpl gradeService;

    @Operation(summary = "Get list of all Grades")
    @GetMapping("/getAllGrades")
    public ResponseEntity<ResponseDataObject> getGrades() {
        List<Grade> classList = gradeService.getGrades();
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.OK, classList,"Successful", true)
        );

    }

    @Operation(summary = "Get Grade By Grade Number")
    @GetMapping("/getGradeByGradeNo/{gradeNo}")
    public ResponseEntity<ResponseDataObject> getGradeByGradeNo(@PathVariable Integer gradeNo) {
        Grade grade = gradeService.getGradeByGradeNo(gradeNo);
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.OK, grade,"Successful", true)
        );
    }

    @Operation(summary = "Add A Grade")
    @PostMapping("/createGrade")
    public ResponseEntity<ResponseDataObject> createGrade(@RequestBody @Valid GradeRequest grade) {
        gradeService.createGrade(grade);
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.CREATED, null,"Successful", true)
        );
    }

    @Operation(summary = "Add more Subjects to a Grade")
    @PutMapping("/addSubjectsToGrade/{gradeNo}")
    public ResponseEntity<ResponseDataObject> addSubjectsToGrade(@PathVariable Integer gradeNo, @RequestBody List<String> subjects) {
        gradeService.addSubjectsToGrade(gradeNo, subjects);
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.CREATED, null ,"Successful", true)
        );
    }

}
