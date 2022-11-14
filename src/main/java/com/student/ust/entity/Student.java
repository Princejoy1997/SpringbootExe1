package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * The type Student.
 */
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

    @NotNull
    private String email;
    @NotNull
    private String password;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    //@OneToOne
    //@JoinColumn(name="book_id")
    //private Book book;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "student")
   private Set<Book> bookSet;

}
