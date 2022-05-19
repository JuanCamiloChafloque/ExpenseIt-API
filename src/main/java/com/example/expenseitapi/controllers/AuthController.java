package com.example.expenseitapi.controllers;

import javax.validation.Valid;

import com.example.expenseitapi.entities.User;
import com.example.expenseitapi.entities.UserModel;
import com.example.expenseitapi.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return new ResponseEntity<>("User is logged in", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserModel uModel) {
        return new ResponseEntity<>(userService.createUser(uModel), HttpStatus.CREATED);
    }

}
