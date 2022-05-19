package com.example.expenseitapi.controllers;

import javax.validation.Valid;

import com.example.expenseitapi.entities.User;
import com.example.expenseitapi.entities.UserModel;
import com.example.expenseitapi.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserModel uModel) {
        return new ResponseEntity<>(userService.createUser(uModel), HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getCurrentUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getCurrentUser(id), HttpStatus.OK);
    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateCurrentUser(@RequestBody User user, @PathVariable("id") Long id) {
        User upUser = userService.updateCurrentUser(user, id);
        return new ResponseEntity<>(upUser, HttpStatus.OK);
    }
}
