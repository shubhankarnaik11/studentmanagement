package com.prat.student.Controller;

import com.prat.student.Entity.Student;
import com.prat.student.Model.StudentRequest;
import com.prat.student.ServiceImpl.StudentServiceImpl;
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
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;
    @Operation(summary = "Get list of all Students")
    @GetMapping("/getAllStudents")
    public ResponseEntity<ResponseDataObject> getAllStudents() {
        List<Student> studentList = studentService.getAllStudents();
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.OK, studentList,"Successful", true)
        );
    }

    @Operation(summary = "Add Student")
    @PostMapping("/createStudent")
    public ResponseEntity<ResponseDataObject> createStudent(@RequestBody StudentRequest newStudent) {
        studentService.createStudent(newStudent);

        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.CREATED, null,"Successful", true)
        );
    }


    @Operation(summary = "Delete Student By Id")
    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity<ResponseDataObject> deleteStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.OK, null,"Successful", true)
        );
    }

    @Operation(summary = "Update Student")
    @PutMapping("/updateStudent")
    public ResponseEntity<ResponseDataObject> updateStudent(@RequestBody @Valid StudentRequest updatedStudent) {
        studentService.updateStudent(updatedStudent);
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.OK, null,"Student Updated Successfully", true)
        );
    }


    @Operation(summary = "Update Student Marks")
    @PutMapping("/updateStudentMarks/{studentId}")
    public ResponseEntity<ResponseDataObject> updateStudentMarks(@PathVariable Integer studentId, @RequestBody HashMap<String, Float> subjectMark) {
        studentService.updateStudentMark(studentId, subjectMark);
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.OK, null,"Student Marks Updated Successfully", true)
        );
    }



}
