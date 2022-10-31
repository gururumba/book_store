package com.example.bookstore.demo.model.dto;

import com.example.bookstore.demo.model.Book;
import com.example.bookstore.demo.model.Writer;
import lombok.Data;

import java.util.List;
@Data
public class WriterDto {

    private int id;
    private String firstName;
    private String lastName;
    private List<Book> books;

    public WriterDto(Writer writer){
        this.firstName = writer.getFirstName();
        this.lastName = writer.getLastName();
        this.books = writer.getBooks();
        this.id = writer.getId();
    }
}
