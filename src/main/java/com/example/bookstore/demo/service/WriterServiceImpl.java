package com.example.bookstore.demo.service;

import com.example.bookstore.demo.model.Writer;
import com.example.bookstore.demo.model.dto.WriterDto;
import com.example.bookstore.demo.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterServiceImpl implements WriterService{

    @Autowired
    WriterRepository writerRepository;
    @Override
    public Writer createWriter(WriterDto writerDto) {
        Writer writer = new Writer();

        writer.setFirstName(writerDto.getFirstName());
        writer.setLastName(writerDto.getLastName());
        writer.setBooks(writerDto.getBooks());

        return writer;
    }

    @Override
    public boolean deleteWriter(int id) {
        Writer writer = writerRepository.findById(id).get();
        writerRepository.delete(writer);

        return true;
    }

    @Override
    public Writer editWriter(WriterDto writerDto) {
        Writer writer = writerRepository.findById(writerDto.getId()).get();

        writer.setBooks(writerDto.getBooks());
        writer.setLastName(writerDto.getLastName());
        writer.setFirstName(writerDto.getFirstName());

        return writer;
    }

    @Override
    public Writer findWriter(int id) {
        return writerRepository.findById(id).get();
    }

    @Override
    public List<Writer> findAllWriters() {
        List<Writer> writers = writerRepository.findAll();
        return writers;
    }
}
