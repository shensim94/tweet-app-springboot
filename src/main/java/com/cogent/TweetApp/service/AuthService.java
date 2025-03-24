package com.cogent.TweetApp.service;

import com.cogent.TweetApp.entity.User;
import com.cogent.TweetApp.payload.AccessToken;
import com.cogent.TweetApp.payload.LoginDTO;
import com.cogent.TweetApp.payload.Message;
import com.cogent.TweetApp.payload.RegisterDTO;

import java.util.List;

public interface AuthService {
    Message registerUser(RegisterDTO user);
    AccessToken login(LoginDTO loginDto);

    List<User> getAllUsers();

    User searchUsername(String username);

    User getUserByUsername(String username);
}
