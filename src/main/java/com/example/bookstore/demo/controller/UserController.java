package com.example.bookstore.demo.controller;

import com.example.bookstore.demo.config.CustomUserDetailsService;
import com.example.bookstore.demo.model.User;
import com.example.bookstore.demo.model.dto.LoginDto;
import com.example.bookstore.demo.model.dto.LoginResponseDto;
import com.example.bookstore.demo.model.dto.UserDto;
import com.example.bookstore.demo.model.dto.UserDtoPassword;
import com.example.bookstore.demo.security.TokenUtil;
import com.example.bookstore.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenUtil tokenUtil;

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
    @GetMapping(path = "/{id}")
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

    @PostMapping(path = "/login")
    public ResponseEntity<?> login (@RequestBody LoginDto loginDTO){

        User user = customUserDetailsService.findUserByEmail(loginDTO.getEmail());

        if(user == null || !passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
            return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
        }
        String token = tokenUtil.generateToken(user.getEmail(),user.getType().toString());
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(token);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> register (@RequestBody UserDtoPassword userDto){

        User user = userService.register(userDto);

        if(user == null){
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @GetMapping(path = "/current")
    public ResponseEntity<?> getCurrentUser(){
        return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.OK);
    }












}
