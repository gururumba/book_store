package com.example.bookstore.demo.repository;

import com.example.bookstore.demo.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends JpaRepository<Writer,Integer> {
}
