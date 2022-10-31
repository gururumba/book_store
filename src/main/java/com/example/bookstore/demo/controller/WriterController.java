package com.example.bookstore.demo.controller;

import com.example.bookstore.demo.model.Writer;
import com.example.bookstore.demo.model.dto.BookDto;
import com.example.bookstore.demo.model.dto.WriterDto;
import com.example.bookstore.demo.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "/api/writer")
public class WriterController {

    @Autowired
    WriterService writerService;

    @PostMapping
    public ResponseEntity<?> createWriter(@RequestBody WriterDto writerDto){
        Writer writer = writerService.createWriter(writerDto);

        return new ResponseEntity<WriterDto>(new WriterDto(writer), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteWriter(@PathVariable int id){
        writerService.deleteWriter(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity<?> editWriter(@RequestBody WriterDto writerDto){
        Writer writer = writerService.editWriter(writerDto);
        return new ResponseEntity<WriterDto>(new WriterDto(writer),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findWriter(@PathVariable int id){
        Writer writer = writerService.findWriter(id);
        return new ResponseEntity<WriterDto>(new WriterDto(writer),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> findAllWriters(){
        List<Writer> writers = writerService.findAllWriters();

        List<WriterDto> writerDtos = new ArrayList<>();
        for(Writer writer : writers){
            WriterDto writerDto = new WriterDto(writer);
            writerDtos.add(writerDto);
        }
        return new ResponseEntity<List<WriterDto>>(writerDtos,HttpStatus.OK);
    }
}
