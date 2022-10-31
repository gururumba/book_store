package com.example.bookstore.demo.model.dto;

import com.example.bookstore.demo.model.Writer;
import lombok.Data;

@Data
public class BookDtoPassword {
    private int id;
    private String title;
    private String description;
    private Writer writer;
    private String password;
}
