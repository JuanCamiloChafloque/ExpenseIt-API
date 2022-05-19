package com.example.expenseitapi.controllers;

import com.example.expenseitapi.entities.User;
import com.example.expenseitapi.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getCurrentUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getCurrentUser(id), HttpStatus.OK);
    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateCurrentUser(@RequestBody User user, @PathVariable("id") Long id) {
        User upUser = userService.updateCurrentUser(user, id);
        return new ResponseEntity<>(upUser, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteCurrentUser(@PathVariable("id") Long id) {
        userService.deleteCurrentUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
