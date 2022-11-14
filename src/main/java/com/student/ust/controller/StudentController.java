package com.student.ust.controller;

import com.student.ust.entity.Student;
import com.student.ust.exception.BusinessException;
import com.student.ust.service.StudentService;
import com.student.ust.util.UstUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Student controller.
 */
@RestController
@Slf4j
public class StudentController {

    /**
     * The Student service.
     */
    @Autowired
    StudentService studentService;

    /**
     * Get response entity.
     *
     * @param studentID the student id
     * @return the response entity
     */
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

    /**
     * Gets student by request param.
     *
     * @param studentID the student id
     * @return the student by request param
     */
    @GetMapping("/students")
    public ResponseEntity<Student> getStudentByRequestParam(@RequestParam(name = "id") Integer studentID){
        try{
            Student student=studentService.getStudentByID(studentID);
            //log.debug("hi log"+studentID); //for debugging
            student.setEmail("");
            student.setPassword("");
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all response entity.
     *
     * @return the response entity
     */
    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAll(){
        try{
            List<Student> studentList=studentService.getAllStudents();
            return new ResponseEntity<List<Student>>(studentList,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update student.
     *
     * @param student the student
     */
    @PutMapping("/student")
    public void updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
    }

    /**
     * Remove.
     *
     * @param studentID the student id
     */
    @DeleteMapping("/student/{studentID}")
    public void remove(@PathVariable Integer studentID ){

        studentService.deleteStudent(studentID);
    }

    /**
     * Add response entity.
     *
     * @param student the student
     * @return the response entity
     */
    @PostMapping("/student")
    public ResponseEntity<Student> add(@RequestBody Student student) {
        try{
            studentService.saveStudent(student);
        }
        catch (BusinessException e) {
            return new ResponseEntity<Student>(HttpStatus.PRECONDITION_FAILED);
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    }

