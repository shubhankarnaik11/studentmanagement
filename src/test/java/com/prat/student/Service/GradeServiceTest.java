package com.prat.student.Service;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Mark;
import com.prat.student.Entity.Student;
import com.prat.student.Entity.Subject;
import com.prat.student.Exception.GradeNotFoundException;
import com.prat.student.Exception.SubjectAlreadyExistsException;
import com.prat.student.Exception.SubjectNotFoundException;
import com.prat.student.Model.GradeRequest;
import com.prat.student.Model.SubjectRequest;
import com.prat.student.Repository.GradeRepository;
import com.prat.student.Repository.MarkRepository;
import com.prat.student.Repository.StudentRepository;
import com.prat.student.Repository.SubjectRepository;
import com.prat.student.ServiceImpl.GradeServiceImpl;
import com.prat.student.ServiceImpl.SubjectServiceImpl;
import io.swagger.v3.oas.models.links.Link;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GradeServiceTest {

    @Mock
    StudentRepository studentRepo;

    @Mock
    GradeRepository gradeRepo;

    @Mock
    SubjectRepository subjectRepo;

    @Mock
    MarkRepository markRepo;

    @InjectMocks
    GradeServiceImpl gradeService;

    @InjectMocks
    SubjectServiceImpl subjectService;

    Integer gradeNo = 10;
    @Mock
    Grade grade = new Grade(gradeNo);
    List<Subject> subjectList = new ArrayList<>();
    Subject s1 = new Subject("math",100f, 35f,3);
    Subject s2 = new Subject("science",100f, 35f,3);



    @BeforeTestClass
    void before(){
        subjectList.add(s1);
        subjectList.add(s2);
        grade.setSubjects(subjectList);
    }
    @Test
    public void getGradeTest(){
        List<Grade> grade = new ArrayList<>();
        grade.add(new Grade(1));
        grade.add(new Grade(2));
        when(gradeRepo.findAll()).thenReturn(grade);

        List<Grade> expectedGradeList = gradeService.getGrades();
        assertEquals(grade, expectedGradeList);

    }

    @Test
    public void getGradeByGradeNoTest(){

        when(gradeRepo.findByGradeNo(gradeNo)).thenReturn(grade);
        Grade expectedGrade = gradeService.getGradeByGradeNo(gradeNo);
        assertEquals(grade,expectedGrade);
    }

    @Test
    public void getGradeStudentsTest(){

        Student student1 = new Student("Name1",1,"Address1",9087654321L,"FatherName","MotherName",grade);
        Student student2 = new Student("Name2",2,"Address2",90876543281L,"Father_Name","Mother_Name",grade);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        when(gradeRepo.findByGradeNo(gradeNo)).thenReturn(grade);
        when(grade.getStudent()).thenReturn(studentList);
        assertEquals(gradeService.getGradeStudents(gradeNo),studentList);

    }

    @Test
    public void createGradeTestThrowsSubjectNotFoundError(){
        when(subjectRepo.findBySubjectName("english")).thenReturn(null);
        when(subjectRepo.findBySubjectName("math")).thenReturn(s1);
        when(subjectRepo.findBySubjectName("science")).thenReturn(s2);

        List<String> subjects = new LinkedList<>();
        subjects.add("math");
        subjects.add("science");
        subjects.add("english");

        GradeRequest grade = new GradeRequest(1, subjects);

        assertThrows(SubjectNotFoundException.class, ()-> gradeService.createGrade(grade));
    }

    @Test
    public void createGradeReturnsGradeWithSubjects(){
        when(subjectRepo.findBySubjectName("english")).thenReturn(s1);
        when(subjectRepo.findBySubjectName("math")).thenReturn(s1);
        when(subjectRepo.findBySubjectName("science")).thenReturn(s2);

        List<String> subjects = new LinkedList<>();
        subjects.add("math");
        subjects.add("science");
        subjects.add("english");

        GradeRequest grade = new GradeRequest(1, subjects);
        Grade newGrade = gradeService.createGrade(grade);

        assertNotNull(newGrade);
        assertFalse(newGrade.getSubjects().isEmpty());
    }

    @Test
    public void createGradeReturnsGradeWithoutSubjects(){

        List<String> subjects = new LinkedList<>();

        GradeRequest grade = new GradeRequest(1, subjects);
        Grade newGrade = gradeService.createGrade(grade);

        assertNotNull(newGrade);
        assertTrue(newGrade.getSubjects().isEmpty());
    }

    @Test
    public void addSubjectsToGradeThrowsGradeNotFoundError(){
        when(gradeRepo.findByGradeNo(10)).thenReturn(null);
        assertThrows(GradeNotFoundException.class, ()-> gradeService.addSubjectsToGrade(10, new LinkedList<>()));
    }

    @Test
    public void addSubjectsToGradeThrowsSubjectNotFoundException(){



        when(gradeRepo.findByGradeNo(10)).thenReturn(grade);



        when(subjectRepo.findBySubjectName("math")).thenReturn(s1);
        when(subjectRepo.findBySubjectName("science")).thenReturn(s2);
        when(subjectRepo.findBySubjectName("english")).thenReturn(null);

        List<String> subjects =  new LinkedList<>();
        subjects.add("math");
        subjects.add("science");
        subjects.add("english");
        assertThrows(SubjectNotFoundException.class , ()-> gradeService.addSubjectsToGrade(10, subjects));
    }

    @Test
    public void addSubjectsToGradeThrowsSubjectAlreadyExistsException(){

        Grade newGrade = new Grade(10);
        Subject newS1 = new Subject("math",100f, 35f,3);

        List<Subject> newSubjectList = new LinkedList<Subject>();
        newSubjectList.add(newS1);
        newGrade.setSubjects(newSubjectList);

        when(gradeRepo.findByGradeNo(10)).thenReturn(newGrade);
        when(subjectRepo.findBySubjectName("math")).thenReturn(s1);

        List<String> subjects =  new LinkedList<>();
        subjects.add("math");

        assertThrows(SubjectAlreadyExistsException.class , ()-> gradeService.addSubjectsToGrade(10, subjects));
    }

    @Test
    public void addSubjectToGradeReturnsGrade(){

        Grade existingGrade = new Grade(10);
        Subject existingS1 = new Subject("math",100f, 35f,3);

        List<Subject> existingSubjectList = new LinkedList<Subject>();
        existingSubjectList.add(existingS1);
        existingGrade.setSubjects(existingSubjectList);

        when(gradeRepo.findByGradeNo(10)).thenReturn(existingGrade);
        when(subjectRepo.findBySubjectName("science")).thenReturn(s2);


        List<String> subjects =  new LinkedList<>();
        subjects.add("science");

        Grade existingGradeWithAddedSubject = gradeService.addSubjectsToGrade(10, subjects);

        assertNotNull(existingGradeWithAddedSubject);
    }


    @Test
    public void getNToppersThrowsGradeNotFoundException(){
        when(gradeRepo.findByGradeNo(10)).thenReturn(null);

        assertThrows( GradeNotFoundException.class , () ->gradeService.getNToppers(10, 5));
    }

    @Test
    public void getNToppers(){
        Subject s1 = new Subject("Maths", 100f, 35f, 3);
        Subject s2 = new Subject("Science", 100f, 35f, 3);
        List <Subject> subjects = new ArrayList<Subject>();
        subjects.add(s1);
        subjects.add(s2);

        Grade grade = new Grade(1,subjects);

        Student student1 = new Student("Krishna", 1,  "Somanath Nilaya, Maravanthe", 9087654321L, "Vijay shanbhag", "Hema", grade);
        Student student2 = new Student("Shubhankar", 2, "Near Raghavendra Swami Temple, JPNagar", 9087654321L, "Father's Name", "Mother's Name", grade);
        List <Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        grade.setStudent(students);

        Mark m1 = new Mark(78f, student1, s1, grade, 1);
        Mark m2 = new Mark(34f, student1, s2, grade, 1);
        Mark m3 = new Mark(100f, student1, s1, grade, 2);
        Mark m4 = new Mark(28f, student1, s2, grade, 2);

        List <Mark> markList = new ArrayList<>();
        markList.add(m1);
        markList.add(m2);
        markList.add(m3);
        markList.add(m4);

        List<List<Number>> toppers = new ArrayList<>();
        List<Number> topper1 = new ArrayList<>();
        topper1.add(1);
        topper1.add(178);

        List<Number> topper2 = new ArrayList<>();
        topper2.add(1);
        topper2.add(62);

        toppers.add(topper1);
        toppers.add(topper2);


        when(gradeRepo.findByGradeNo(1)).thenReturn(grade);
        when(gradeRepo.getToppers(1, 2023)).thenReturn(toppers);
        when(studentRepo.findByStudentId(1)).thenReturn(student1);


        assertNotNull(gradeService.getNToppers(1,5));


    }

    @Test
    public void promoteAllStudentsByGradeThrowsGradeNotFoundError(){

        when(gradeRepo.findByGradeNo(10)).thenReturn(null);

        assertThrows( GradeNotFoundException.class , () ->gradeService.promoteAllStudentsByGrade(10));
    }

    @Test
    public void promoteAllStudentByGrade(){
        Subject s1 = new Subject("Maths", 100f, 35f, 3);
        Subject s2 = new Subject("Science", 100f, 35f, 3);
        List <Subject> subjects = new ArrayList<Subject>();
        subjects.add(s1);
        subjects.add(s2);

        Grade grade1 = new Grade(1,subjects);
        Grade grade2 = new Grade(2,subjects);

        Student student1 = new Student("Krishna", 1,  "Somanath Nilaya, Maravanthe", 9087654321L, "Vijay shanbhag", "Hema", grade1);
        Student student2 = new Student("Shubhankar", 2, "Near Raghavendra Swami Temple, JPNagar", 9087654321L, "Father's Name", "Mother's Name", grade1);
        Student student3 = new Student("Shubhankar2", 2, "Near Raghavendra Swami Temple, JPNagar", 9087654321L, "Father's Name", "Mother's Name", grade1);
        student1.setStudentId(1);
        student1.setPreviousGrades(new ArrayList<>());
        student2.setStudentId(2);
        student2.setPreviousGrades(new ArrayList<>());
        student3.setStudentId(3);
        student3.setPreviousGrades(new ArrayList<>());

        Student student1Promoted = new Student("Krishna", 1,  "Somanath Nilaya, Maravanthe", 9087654321L, "Vijay shanbhag", "Hema", grade2);

        List <Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        grade1.setStudent(students);

        Mark m1 = new Mark(78f, student1, s1, grade1, 1);
        Mark m2 = new Mark(34f, student2, s1, grade1, 1);
        Mark m3 = new Mark(100f, student1, s2, grade1, 2);
        Mark m4 = new Mark(28f, student2, s2, grade1, 2);

        List <Mark> markList = new ArrayList<>();
        markList.add(m1);
        markList.add(m2);
        markList.add(m3);
        markList.add(m4);

        List <Mark> student1MarkList = new ArrayList<>();
        student1MarkList.add(m1);
        student1MarkList.add(m3);

        List <Mark> student2MarkList = new ArrayList<>();
        student2MarkList.add(m2);
        student2MarkList.add(m4);

        List<List<Number>> toppers = new ArrayList<>();
        List<Number> topper1 = new ArrayList<>();
        topper1.add(1);
        topper1.add(178);

        List<Number> topper2 = new ArrayList<>();
        topper2.add(1);
        topper2.add(62);

        toppers.add(topper1);
        toppers.add(topper2);


        when(gradeRepo.findByGradeNo(1)).thenReturn(grade1);
        when(gradeRepo.findByGradeNo(2)).thenReturn(grade2);

//        when(gradeRepo.getToppers(1, 2023)).thenReturn(toppers);
        when(studentRepo.findByStudentId(1)).thenReturn(student1);
        when(studentRepo.findByStudentId(2)).thenReturn(student2);
        when(studentRepo.findByStudentId(3)).thenReturn(student3);

        when(markRepo.findByStudentAndAcademicYear(student1, 2023)).thenReturn(student1MarkList);
        when(markRepo.findByStudentAndAcademicYear(student2, 2023)).thenReturn(student2MarkList);
        when(markRepo.findByStudentAndAcademicYear(student3, 2023)).thenReturn(new ArrayList<>());

        List<HashMap <String, Object>> promotedList = new ArrayList<HashMap <String, Object>>();
        HashMap<String, Object> retObject1 = new HashMap<String, Object>();
        retObject1.put("promoted", true);
        retObject1.put("Student", student1Promoted);

        HashMap<String, Object> retObject2 = new HashMap<String, Object>();
        retObject2.put("Promoted", false);
        retObject2.put("Student", student2);

        HashMap<String, Object> retObject3 = new HashMap<String, Object>();
        retObject2.put("Promoted", false);
        retObject2.put("Student", student2);

        promotedList.add(retObject1);
        promotedList.add(retObject2);
        promotedList.add(retObject3);

        List<HashMap <String, Object>> newPromotedList = gradeService.promoteAllStudentsByGrade(1);


        assertNotNull(newPromotedList);

        assertEquals(3, newPromotedList.size());
        assertTrue((Boolean) newPromotedList.get(0).get("Promoted"));
        assertFalse((Boolean) newPromotedList.get(2).get("Promoted"));

        Student newStudent1 = (Student) newPromotedList.get(0).get("Student");
        assertEquals(2, (int) newStudent1.getCurrentGrade().getGradeNo());
        assertEquals(1, (int) newStudent1.getPreviousGrades().get(0).getGradeNo());

        assertFalse((Boolean) newPromotedList.get(1).get("Promoted"));
        Student newStudent2 = (Student) newPromotedList.get(2).get("Student");
        assertEquals(1, (int) newStudent2.getCurrentGrade().getGradeNo());
        assertTrue(newStudent2.getPreviousGrades().isEmpty());

        assertFalse((Boolean) newPromotedList.get(2).get("Promoted"));
        Student newStudent3 = (Student) newPromotedList.get(2).get("Student");
        assertEquals(1, (int) newStudent2.getCurrentGrade().getGradeNo());
        assertTrue(newStudent2.getPreviousGrades().isEmpty());

    }
}

