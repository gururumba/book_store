package com.example.bookstore.demo.service;

import com.example.bookstore.demo.model.Writer;
import com.example.bookstore.demo.model.dto.WriterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WriterService {

    Writer createWriter(WriterDto writerDto);

    boolean deleteWriter(int id);

    Writer editWriter(WriterDto writerDto);

    Writer findWriter(int id);

    List<Writer> findAllWriters();
}
