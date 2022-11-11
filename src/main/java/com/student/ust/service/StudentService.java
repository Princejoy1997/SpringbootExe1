package com.student.ust.service;

import com.student.ust.entity.Student;
import com.student.ust.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public Student getStudentByID(Integer studentID)throws NoSuchElementException {
        //System.out.println(studentRepository.findByName("jayanth").getName());
        return studentRepository.findById(studentID).orElseThrow(()->new NoSuchElementException());
    }

    public Student saveStudent(Student student) {

        student.setCreateDate(LocalDateTime.now());
        student.setModifyDate(student.getCreateDate());
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public void deleteStudent(int studentID) {
        studentRepository.deleteById(studentID);
    }

    public Student updateStudent(Student student){
        Student updateStudent= studentRepository.findById(student.getStudentID()).orElseThrow(()->new NoSuchElementException());

        updateStudent.setName(student.getName());
        updateStudent.setAge(student.getAge());
        updateStudent.setRollNo(student.getRollNo());
        updateStudent.setModifyDate(LocalDateTime.now());

        studentRepository.save(updateStudent);
        return updateStudent;
    }


}
