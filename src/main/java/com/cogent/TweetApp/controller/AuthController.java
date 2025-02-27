package com.cogent.TweetApp.controller;


import com.cogent.TweetApp.entity.User;
import com.cogent.TweetApp.payload.LoginDto;
import com.cogent.TweetApp.payload.RegisterDto;
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

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {
        String response = authService.registerUser(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        String response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{username}/forgot")
    public ResponseEntity<?> forgotPassword(@PathVariable("username") String username) {
        return null;
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = authService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/search/{username}")
    public ResponseEntity<User> searchByUsername(@PathVariable("username") String username) {
        User user = authService.searchUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
