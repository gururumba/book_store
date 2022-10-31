package com.example.bookstore.demo.controller;

import com.example.bookstore.demo.model.Book;
import com.example.bookstore.demo.model.Writer;
import com.example.bookstore.demo.model.dto.BookDto;
import com.example.bookstore.demo.model.dto.BookDtoPassword;
import com.example.bookstore.demo.model.dto.WriterDto;
import com.example.bookstore.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookDto bookDto){
        Book book = bookService.createBook(bookDto);
        return new ResponseEntity<BookDto>(new BookDto(book), HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id){
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> editBook(@RequestBody BookDtoPassword bookDtoPassword){
        Book book = bookService.editBook(bookDtoPassword);
        return new ResponseEntity<BookDto>(new BookDto(book),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> findBook(@RequestParam int id){
        Book book = bookService.findBook(id);
        return new ResponseEntity<BookDto>(new BookDto(book),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> findBooks(){
        List<Book> bookList = bookService.findBooks();

        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book : bookList){
            BookDto bookDto = new BookDto(book);
            bookDtos.add(bookDto);
        }

        return new ResponseEntity<List<BookDto>>(bookDtos,HttpStatus.OK);
    }

}
