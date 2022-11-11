package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "book_mappedby")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookID;

    private String bookName;
    private String bookAuthorName;
    private int bookIsbnNo;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;
}
