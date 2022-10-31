package com.example.bookstore.demo.service;

import com.example.bookstore.demo.model.User;
import com.example.bookstore.demo.model.dto.UserDto;
import com.example.bookstore.demo.model.dto.UserDtoPassword;
import com.example.bookstore.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public User createUser(UserDto userDto) {
        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setType(userDto.getType());

        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(int id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return true;
    }

    @Override
    public User editUser(UserDtoPassword userDtoPassword) {
        User user = userRepository.findById(userDtoPassword.getId()).get();

        user.setType(userDtoPassword.getType());
        user.setLastName(userDtoPassword.getLastName());
        user.setFirstName(userDtoPassword.getFirstName());
        user.setEmail(userDtoPassword.getEmail());

        return user;
    }

    @Override
    public User findUser(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
