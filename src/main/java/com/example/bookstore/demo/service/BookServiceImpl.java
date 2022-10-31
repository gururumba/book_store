package com.example.bookstore.demo.service;

import com.example.bookstore.demo.model.Book;
import com.example.bookstore.demo.model.dto.BookDto;
import com.example.bookstore.demo.model.dto.BookDtoPassword;
import com.example.bookstore.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book createBook(BookDto bookDto) {
        Book book = new Book();
        book.setDescription(bookDto.getDescription());
        book.setTitle(bookDto.getTitle());
        book.setWriter(bookDto.getWriter());

        return bookRepository.save(book);
    }

    @Override
    public boolean deleteBook(int id) {
        Book book = bookRepository.findById(id).get();
        bookRepository.delete(book);
        return true;
    }

    @Override
    public Book editBook(BookDtoPassword bookDto) {
        Book book = bookRepository.findById(bookDto.getId()).get();

        book.setWriter(bookDto.getWriter());
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());

        return book;
    }

    @Override
    public Book findBook(int id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> findBooks() {
        return bookRepository.findAll();
    }
}
