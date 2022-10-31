package com.example.bookstore.demo.model.dto;

import com.example.bookstore.demo.model.User;
import com.example.bookstore.demo.model.UserType;
import lombok.Data;

@Data
public class UserDtoPassword {

    private int id;

    private String email;
    private String firstName;
    private String lastName;
    private UserType type;

    private String password;

    public UserDtoPassword(User user) {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.type = user.getType();
        this.password = user.getPassword();
    }
}
