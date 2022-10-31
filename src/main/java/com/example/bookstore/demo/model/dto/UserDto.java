package com.example.bookstore.demo.model.dto;

import com.example.bookstore.demo.model.User;
import com.example.bookstore.demo.model.UserType;
import lombok.Data;

@Data
public class UserDto {

    private int id;

    private String email;
    private String firstName;
    private String lastName;
    private UserType type;

    public UserDto(User user) {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.type = user.getType();
    }
}
