package com.example.bookstore.demo.service;

import com.example.bookstore.demo.model.Book;
import com.example.bookstore.demo.model.Writer;
import com.example.bookstore.demo.model.dto.BookDto;
import com.example.bookstore.demo.repository.BookRepository;
import com.example.bookstore.demo.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;
    WriterRepository writerRepository;

    @Override
    public Book createBook(BookDto bookDto) {
        Book book = new Book();
        book.setDescription(bookDto.getDescription());
        book.setTitle(bookDto.getTitle());
        Writer writer = writerRepository.findById(bookDto.getWriter()).get();
        book.setWriter(writer);

        return bookRepository.save(book);
    }

    @Override
    public boolean deleteBook(int id) {
        Book book = bookRepository.findById(id).get();
        bookRepository.delete(book);
        return true;
    }

    @Override
    public Book editBook(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getId()).get();

        Writer writer = writerRepository.findById(bookDto.getWriter()).get();
        book.setWriter(writer);
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());

        return book;
    }

    @Override
    public Book findBook(int id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> searchBooks() {
        return bookRepository.findAll();
    }
}
