package com.example.bookstore.demo.model.dto;

import com.example.bookstore.demo.model.Book;
import com.example.bookstore.demo.model.Writer;
import lombok.Data;

@Data
public class BookDto {

    private int id;
    private String title;
    private String description;
    private Writer writer;

    public BookDto(Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.writer = book.getWriter();
    }
}
