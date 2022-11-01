package com.example.bookstore.demo.model.dto;

import com.example.bookstore.demo.model.Book;
import com.example.bookstore.demo.model.BookRental;
import com.example.bookstore.demo.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class BookRentalDto {

    private int id;
    private Date start;
    private Date end;

    private Book book;
    private User user;

    public BookRentalDto(BookRental bookRental){
        this.id = bookRental.getId();
        this.start = bookRental.getStart();
        this.end = bookRental.getEnd();
        this.book = bookRental.getBook();
        this.user = bookRental.getUser();
    }
}
