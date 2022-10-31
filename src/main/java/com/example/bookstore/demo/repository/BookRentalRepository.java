package com.example.bookstore.demo.repository;

import com.example.bookstore.demo.model.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRentalRepository extends JpaRepository<BookRental,Integer> {
}
