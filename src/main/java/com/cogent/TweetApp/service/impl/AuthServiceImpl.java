package com.cogent.TweetApp.service.impl;

import com.cogent.TweetApp.entity.Role;
import com.cogent.TweetApp.entity.User;
import com.cogent.TweetApp.payload.LoginDto;
import com.cogent.TweetApp.payload.RegisterDto;
import com.cogent.TweetApp.repository.RoleRepository;
import com.cogent.TweetApp.repository.UserRepository;
import com.cogent.TweetApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String registerUser(RegisterDto registerDto) {
        // check for username exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }
        // check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new RuntimeException("Email is already taken!");
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        // save the user to the database


        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "User successfully registered";
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
        );
        System.out.println("is authenticated: " + authentication.isAuthenticated());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Login successful!";
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User searchUsername(String username) {
        return null;
    }
}
