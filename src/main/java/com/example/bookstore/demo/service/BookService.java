package com.example.bookstore.demo.service;

import com.example.bookstore.demo.model.Book;
import com.example.bookstore.demo.model.dto.BookDto;
import com.example.bookstore.demo.model.dto.BookDtoPassword;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    public Book createBook(BookDto bookDto);

    public boolean deleteBook(int id);

    public Book editBook(BookDtoPassword bookDto);

    Book findBook(int id);

    List<Book> findBooks();
}
