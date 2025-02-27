package com.cogent.TweetApp.service;

import com.cogent.TweetApp.entity.User;
import com.cogent.TweetApp.payload.LoginDto;
import com.cogent.TweetApp.payload.RegisterDto;

import java.util.List;

public interface AuthService {
    String registerUser(RegisterDto user);
    String login(LoginDto loginDto);

    List<User> getAllUsers();

    User searchUsername(String username);

}
