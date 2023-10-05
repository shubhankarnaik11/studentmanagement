package com.prat.student.controller;

import com.prat.student.entity.Student;
import com.prat.student.model.StudentRequest;
import com.prat.student.serviceimpl.StudentServiceImpl;
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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;


    @Operation(summary = "Get list of all Students")
    @GetMapping("/get")
    public ResponseEntity<ResponseDataObject> getAllStudents() {
        List<Student> studentList = studentService.getAllStudents();
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, studentList,"Successful", true));
    }

    @Operation
    @GetMapping("/get/{studentId}")
    public ResponseEntity<ResponseDataObject> getStudentById(@PathVariable Integer studentId){
        Student student = studentService.getStudentById(studentId);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, student,"Successful", true));
    }

    @Operation(summary = "Add Student")
    @PostMapping("/create")
    public ResponseEntity<ResponseDataObject> createStudent(@RequestBody StudentRequest newStudent) {


        Student student = studentService.createStudent(newStudent);

        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.CREATED, student,"Successful", true));

    }


    @Operation(summary = "Delete Student By Id")
    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<ResponseDataObject> deleteStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, studentId,"Deleted student with Id"+studentId, true));
    }

    @Operation(summary = "Update Student")
    @PutMapping("/update")
    public ResponseEntity<ResponseDataObject> updateStudent(@RequestBody @Valid StudentRequest updatedStudent) {
        Student student = studentService.updateStudent(updatedStudent);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, student,"Student Updated Successfully", true));
    }


    @Operation(summary = "Update Student Marks")
    @PutMapping("/update-marks/{studentId}")
    public ResponseEntity<ResponseDataObject> updateStudentMarks(@PathVariable Integer studentId, @RequestBody HashMap<String, Float> subjectMark) {
        studentService.updateStudentMark(studentId, subjectMark);
        return ResponseObject.getResponseObject(new ResponseDataObject(HttpStatus.OK, null,"Student Marks Updated Successfully", true));
    }


}
