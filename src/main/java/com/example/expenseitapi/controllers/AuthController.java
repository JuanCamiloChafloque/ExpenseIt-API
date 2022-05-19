package com.example.expenseitapi.controllers;

import javax.validation.Valid;

import com.example.expenseitapi.entities.LoginModel;
import com.example.expenseitapi.entities.User;
import com.example.expenseitapi.entities.UserModel;
import com.example.expenseitapi.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authManager;
    
    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody LoginModel data) {
        Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserModel uModel) {
        return new ResponseEntity<>(userService.createUser(uModel), HttpStatus.CREATED);
    }

}
