package com.example.bookstore.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookRental {

    @Id
    private int id;
    private Date start;
    private Date end;

    @ManyToOne
    private Book book;
    @ManyToOne
    private User user;


}
