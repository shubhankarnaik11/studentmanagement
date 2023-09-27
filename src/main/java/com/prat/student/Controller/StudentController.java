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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;


    @Operation(summary = "Get list of all Students")
    @GetMapping("/get-all-students")
    public ResponseEntity<ResponseDataObject> getAllStudents() {
        List<Student> studentList = studentService.getAllStudents();
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.OK, studentList,"Successful", true)
        );
    }

    @Operation
    @GetMapping("/get-student/{studentId}")
    public ResponseEntity<ResponseDataObject> getStudentById(@PathVariable Integer studentId){
        Student student = studentService.getStudentById(studentId);
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.OK, student,"Successful", true)
        );
    }

    @Operation(summary = "Add Student")
    @PostMapping("/create-student")
    public ResponseEntity<ResponseDataObject> createStudent(@RequestBody StudentRequest newStudent) {
        Student student = studentService.createStudent(newStudent);

        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.CREATED, student,"Successful", true)
        );
    }


    @Operation(summary = "Delete Student By Id")
    @DeleteMapping("/delete-student/{studentId}")
    public ResponseEntity<ResponseDataObject> deleteStudent(@PathVariable Integer studentId) {
        Integer studentId1 = studentService.deleteStudent(studentId);
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.OK, studentId1,"Deleted student with Id"+studentId, true)
        );
    }

    @Operation(summary = "Update Student")
    @PutMapping("/update-student")
    public ResponseEntity<ResponseDataObject> updateStudent(@RequestBody @Valid StudentRequest updatedStudent) {
        Student student = studentService.updateStudent(updatedStudent);
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.OK, student,"Student Updated Successfully", true)
        );
    }


    @Operation(summary = "Update Student Marks")
    @PutMapping("/update-student-marks/{studentId}")
    public ResponseEntity<ResponseDataObject> updateStudentMarks(@PathVariable Integer studentId, @RequestBody HashMap<String, Float> subjectMark) {
        studentService.updateStudentMark(studentId, subjectMark);
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.OK, null,"Student Marks Updated Successfully", true)
        );
    }


}
