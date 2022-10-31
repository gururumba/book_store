package com.example.bookstore.demo.model.dto;

import com.example.bookstore.demo.model.Book;
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
}
