package com.example.bookstore.demo.controller;

import com.example.bookstore.demo.model.User;
import com.example.bookstore.demo.model.dto.UserDto;
import com.example.bookstore.demo.model.dto.UserDtoPassword;
import com.example.bookstore.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        User user = userService.createUser(userDto);

        return new ResponseEntity<UserDto>(new UserDto(user), HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity<?> editUser(@RequestBody UserDtoPassword userDtoPassword){
        User user = userService.editUser(userDtoPassword);

        return new ResponseEntity<UserDto>(new UserDto(user), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> findUser(@PathVariable int id){
        User user = userService.findUser(id);

        return new ResponseEntity<UserDto>(new UserDto(user), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> findUsers(){
        List<User> userList = userService.findAllUsers();

        List<UserDto> userDtos = new ArrayList<>();
        for(User user : userList){
            UserDto userDto = new UserDto(user);
            userDtos.add(userDto);
        }
        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
    }
}
