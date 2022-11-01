package com.example.bookstore.demo.service;

import com.example.bookstore.demo.model.BookRental;
import com.example.bookstore.demo.model.dto.BookRentalDto;
import com.example.bookstore.demo.repository.BookRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookRentalServiceImpl implements BookRentalService {

    @Autowired
    BookRentalRepository bookRentalRepository;
    @Override
    public BookRental createBookRental(BookRentalDto bookRentalDto) {
        BookRental bookRental = new BookRental();

        bookRental.setBook(bookRentalDto.getBook());
        bookRental.setEnd(bookRentalDto.getEnd());
        bookRental.setStart(bookRentalDto.getStart());
        bookRental.setUser(bookRentalDto.getUser());

        return bookRentalRepository.save(bookRental);
    }

    @Override
    public boolean deleteBook(int id) {
        BookRental bookRental = bookRentalRepository.findById(id).get();
        bookRentalRepository.delete(bookRental);
        return true;
    }
}
