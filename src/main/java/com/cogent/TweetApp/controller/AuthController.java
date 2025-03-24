package com.cogent.TweetApp.controller;


import com.cogent.TweetApp.entity.User;
import com.cogent.TweetApp.mapper.EntityToDTOMapper;
import com.cogent.TweetApp.payload.*;
import com.cogent.TweetApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1.0/tweets")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EntityToDTOMapper entityToDTOMapper;

    @PostMapping("/register")
    public ResponseEntity<Message> registerUser(@RequestBody RegisterDTO registerDto) {
        Message response = authService.registerUser(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AccessToken> login(@RequestBody LoginDTO loginDto) {
        System.out.println("hello from login controller");
        AccessToken token = authService.login(loginDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/{username}/forgot")
    public ResponseEntity<?> forgotPassword(@PathVariable("username") String username) {
        return null;
    }

    @GetMapping("/{username}/get")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {
        User user = authService.getUserByUsername(username);
        UserDTO userDTO = entityToDTOMapper.toUserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = authService.getAllUsers();
        List<UserDTO> userDTOS = users.stream().map(e-> entityToDTOMapper.toUserDTO(e)).toList();
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/user/search/{username}")
    public ResponseEntity<User> searchByUsername(@PathVariable("username") String username) {
        User user = authService.searchUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
