package com.student.ust.service;

import com.student.ust.entity.Student;
import com.student.ust.exception.InvalidEmail;
import com.student.ust.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Student service.
 */
@Service
public class StudentService {

    /**
     * The Student repository.
     */
    @Autowired
    StudentRepository studentRepository;

    /**
     * Gets student by id.
     *
     * @param studentID the student id
     * @return the student by id
     * @throws NoSuchElementException the no such element exception
     */
    public Student getStudentByID(Integer studentID)throws NoSuchElementException {
        //System.out.println(studentRepository.findByName("jayanth").getName());
        return studentRepository.findById(studentID).orElseThrow(()->new NoSuchElementException());
    }

    /**
     * Save student student.
     *
     * @param student the student
     * @return the student
     */
    public Student saveStudent(Student student) {

        student.setCreateDate(LocalDateTime.now());
        student.setModifyDate(student.getCreateDate());
        return studentRepository.save(student);
    }

    /**
     * Gets all students.
     *
     * @return the all students
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    /**
     * Delete student.
     *
     * @param studentID the student id
     */
    public void deleteStudent(int studentID) {
        studentRepository.deleteById(studentID);
    }

    /**
     * Update student student.
     *
     * @param student the student
     * @return the student
     */
    public Student updateStudent(Student student){
        Student updateStudent= studentRepository.findById(student.getStudentID()).orElseThrow(()->new NoSuchElementException());

        updateStudent.setName(student.getName());
        updateStudent.setAge(student.getAge());
        updateStudent.setRollNo(student.getRollNo());
        updateStudent.setModifyDate(LocalDateTime.now());

        studentRepository.save(updateStudent);
        return updateStudent;
    }


    /**
     * Email validate int.
     *
     * @param email the email
     * @return the int
     */
    public int emailValidate(String email) {
        String regex="^([A-Za-z0-9+_.-]+)@([A-Za-z0-9]+)\\.([a-z])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()==true){
            return 0;
        }
        else {
            throw new InvalidEmail();
        }
    }

    /**
     * Password validate int.
     *
     * @param password the password
     * @return the int
     */
    public int passwordValidate(String password) {
        return passwordValidate(password);
    }
}
