package com.example.bookstore.demo.service;

import com.example.bookstore.demo.model.User;
import com.example.bookstore.demo.model.dto.UserDto;
import com.example.bookstore.demo.model.dto.UserDtoPassword;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User createUser(UserDto userDto);

    boolean deleteUser(int id);

    User editUser(UserDtoPassword userDtoPassword);

    User findUser(int id);

    List<User> findAllUsers();
}
