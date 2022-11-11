package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "student_mappedby")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentID;

    private String name;
    private int age;
    private int rollNo;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    String emailAddress;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    //@OneToOne
    //@JoinColumn(name="book_id")
    //private Book book;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "student")
   private Set<Book> bookSet;

}
