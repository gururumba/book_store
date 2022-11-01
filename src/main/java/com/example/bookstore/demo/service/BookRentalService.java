package com.example.bookstore.demo.service;

import com.example.bookstore.demo.model.BookRental;
import com.example.bookstore.demo.model.dto.BookRentalDto;
import org.springframework.stereotype.Service;

@Service
public interface BookRentalService {

    BookRental createBookRental(BookRentalDto bookRentalDto);

    boolean deleteBook(int id);
}
