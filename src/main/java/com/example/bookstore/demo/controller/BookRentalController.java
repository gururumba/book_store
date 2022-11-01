package com.example.bookstore.demo.controller;

import com.example.bookstore.demo.model.BookRental;
import com.example.bookstore.demo.model.dto.BookRentalDto;
import com.example.bookstore.demo.service.BookRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/bookRental")
public class BookRentalController {


    @Autowired
    BookRentalService bookRentalService;

    public ResponseEntity<?> createBookRental(@RequestBody BookRentalDto bookRentalDto){
        BookRental bookRental = bookRentalService.createBookRental(bookRentalDto);

        return new ResponseEntity<BookRentalDto>(new BookRentalDto(bookRental), HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id){
        bookRentalService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
