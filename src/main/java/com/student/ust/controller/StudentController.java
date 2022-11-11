package com.student.ust.controller;

import com.student.ust.entity.Student;
import com.student.ust.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/student/{studentID}")
    public ResponseEntity<Student>get(@PathVariable Integer studentID){
        try{
            Student student=studentService.getStudentByID(studentID);
            //log.debug("hi log"+studentID); //for debugging
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/students")
    public ResponseEntity<Student> getStudentByRequestParam(@RequestParam(name = "id") Integer studentID){
        try{
            Student student=studentService.getStudentByID(studentID);
            //log.debug("hi log"+studentID); //for debugging
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAll(){
        try{
            List<Student> studentList=studentService.getAllStudents();
            return new ResponseEntity<List<Student>>(studentList,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/student")
    public void updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
    }

    @DeleteMapping("/student/{studentID}")
    public void remove(@PathVariable Integer studentID ){
        studentService.deleteStudent(studentID);
    }

    @PostMapping("/student")
    public void add(@RequestBody Student student){
        studentService.saveStudent(student);
    }

}
