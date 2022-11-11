package com.student.ust.controller;

import com.student.ust.entity.Book;
import com.student.ust.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/book/{bookID}")
    public ResponseEntity<Book>get(@PathVariable Integer bookID){
        try{
            Book book=bookService.getBookById(bookID);
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/book")

    public ResponseEntity<List<Book>> getAll(){
        try{
            List<Book> bookList=bookService.getAllBooks();
            return new ResponseEntity<List<Book>>(bookList,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/book/{bookID}")
    public void remove(@PathVariable Integer bookID ){
        bookService.deleteBook(bookID);
    }

    @PutMapping("/book")
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }

    @PostMapping("/book")
    public void add(@RequestBody Book book){
        bookService.saveBook(book);
    }
}
