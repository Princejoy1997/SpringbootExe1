package com.student.ust.service;

import com.student.ust.dto.StudentDto;
import com.student.ust.entity.Student;
import com.student.ust.exception.BusinessException;
import com.student.ust.exception.InvalidEmail;
import com.student.ust.exception.InvalidPassword;
import com.student.ust.repository.StudentRepository;
import com.student.ust.util.UstUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static com.student.ust.util.UstUtil.hashPassword;

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

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Gets student by id.
     *
     * @param studentID the student id
     * @return the student by id
     * @throws NoSuchElementException the no such element exception
     */
    public Student getStudentByID(Integer studentID)throws NoSuchElementException {

        Student student= studentRepository.findById(studentID).orElseThrow(()->new NoSuchElementException());
        //System.out.println(studentRepository.findByName("jayanth").getName());
        student.setEmail("");
        student.setPassword("");
        return student;
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

        int resultEmail= UstUtil.emailValidate(student.getEmail());
        int resultPassword=UstUtil.passwordValidate(student.getPassword());
        if(resultEmail==0 && resultPassword== 0){
            student.setPassword(hashPassword(student.getPassword()));
            studentRepository.save(student);
        }
        else {
            throw new BusinessException("Invalid Email or Password");
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK).getBody();
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

    public StudentDto convertToDto(Student student){
        return modelMapper.map(student,StudentDto.class);
    }
}
