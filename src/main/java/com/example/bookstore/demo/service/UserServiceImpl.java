package com.example.bookstore.demo.service;

import com.example.bookstore.demo.config.SecurityUtil;
import com.example.bookstore.demo.model.User;
import com.example.bookstore.demo.model.UserType;
import com.example.bookstore.demo.model.dto.UserDto;
import com.example.bookstore.demo.model.dto.UserDtoPassword;
import com.example.bookstore.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    public User register(UserDtoPassword userDto) {

        User user = userRepository.getByEmail(userDto.getEmail());
        if(user != null){
            return null;
        }
        user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(user.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setType(UserType.EMPLOYEE);

        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        String email = SecurityUtil.getCurrentUserLogin().get();

        return userRepository.getByEmail(email);
    }
}
