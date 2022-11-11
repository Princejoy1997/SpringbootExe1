package com.student.ust.service;

import com.student.ust.entity.Book;
import com.student.ust.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;


    public Book getBookById(Integer bookID) {

        return bookRepository.findById(bookID).orElse(null);
    }

    public Book saveBook(Book book) {

        book.setCreateDate(LocalDateTime.now());
        book.setModifyDate(book.getCreateDate());
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    public Book updateBook(Book book) {
        Book updateBook = bookRepository.findById(book.getBookID()).orElseThrow(() -> new NoSuchElementException());


        updateBook.setBookName(book.getBookName());
        updateBook.setBookName(book.getBookName());
        updateBook.setBookName(book.getBookName());
        updateBook.setModifyDate(LocalDateTime.now());

        bookRepository.save(updateBook);
        return updateBook;
    }

    public void deleteBook(Integer bookID) {
        bookRepository.deleteById(bookID);
    }
}
