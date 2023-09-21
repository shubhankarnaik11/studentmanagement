package com.prat.student.Controller;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Student;
import com.prat.student.Entity.Subject;
import com.prat.student.Model.GradeRequest;
import com.prat.student.Model.StudentRequest;
import com.prat.student.Model.SubjectRequest;
import com.prat.student.ServiceImpl.GradeServiceImpl;
import com.prat.student.ServiceImpl.StudentServiceImpl;
import com.prat.student.ServiceImpl.SubjectServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/studentmgt")
public class StudentManagementController {

    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    GradeServiceImpl gradeService;

    @Autowired
    SubjectServiceImpl subjectService;


    @Operation(summary = "Get list of all Students")
    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> studentList = studentService.getAllStudents();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @Operation(summary = "Get Student By Id")
    @GetMapping("/getStudent/{studentId}")
    public ResponseEntity<Optional<Student>> getStudent(@PathVariable Integer studentId) {
        Optional<Student> student = studentService.getStudentById(studentId);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @Operation(summary = "Add Student")
    @PostMapping("/createStudent")
    public ResponseEntity<String> createStudent(@RequestBody StudentRequest newStudent) {

        studentService.createStudent(newStudent);
        return new ResponseEntity<>("Student Created Successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Delete Student By Id")
    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer studentId) {

        studentService.deleteStudent(studentId);
        return new ResponseEntity<>("Student Deleted Successfully", HttpStatus.OK);
    }

    @Operation(summary = "Update Student")
    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody StudentRequest updatedStudent) {
        studentService.updateStudent(updatedStudent);
        return new ResponseEntity<>("Student Updated Successfully", HttpStatus.OK);
    }

    @Operation(summary = "Update Student Marks")
    @PutMapping("/updateStudentMarks/{studentId}")
    public ResponseEntity<String> updateStudentMarks(@PathVariable Integer studentId, @RequestBody HashMap<String, Float> subjectMark) {
        studentService.updateStudentMark(studentId, subjectMark);
        return new ResponseEntity<>("Updated Marks", HttpStatus.OK);
    }

    @Operation(summary = "Get list of all Grades")
    @GetMapping("/getAllGrade")
    public ResponseEntity<List<?>> getGrades() {
        List<Grade> classList = gradeService.getGrades();
        return new ResponseEntity<>(classList, HttpStatus.OK);
    }

    @Operation(summary = "Get Grade By Grade Number")
    @GetMapping("/getGradeById/{gradeNo}")
    public ResponseEntity<Optional<Grade>> getGradeById(@PathVariable Integer gradeNo) {
        Optional<Grade> grade = gradeService.getGradeById(gradeNo);

        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @Operation(summary = "Add A Grade")
    @PostMapping("/createGrade")
    public ResponseEntity<String> createGrade(@RequestBody GradeRequest grade) {
        gradeService.createGrade(grade);
        return new ResponseEntity<>("Grade Created Successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Add more Subjects to a Grade")
    @PutMapping("/addSubjectsToGrade/{gradeNo}")
    public ResponseEntity<String> addSubjectsToGrade(@PathVariable Integer gradeNo, @RequestBody List<String> subjects) {
        gradeService.addSubjectsToGrade(gradeNo, subjects);
        return new ResponseEntity<>("Subjects Added to Grade Successfully", HttpStatus.OK);
    }


    @Operation(summary = "Get list of all Subjects")
    @GetMapping("/getAllSubjects")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjectList = subjectService.getAllSubjects();
        return new ResponseEntity<>(subjectList, HttpStatus.OK);
    }

    @Operation(summary = "Get Subject By Id")
    @GetMapping("/getSubject/{subjectId}")
    public ResponseEntity<Optional<Subject>> getSubject(@PathVariable Integer subjectId) {
        Optional<Subject> subject = subjectService.getSubjectById(subjectId);

        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @Operation(summary = "Add new Subject")
    @PostMapping("/createSubject")
    public ResponseEntity<String> createSubject(@Valid @RequestBody SubjectRequest subject) {
        subjectService.createSubject(subject);
        return new ResponseEntity<>(" Subject Created Successfully", HttpStatus.CREATED);
    }


}