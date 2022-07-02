package com.example.expenseitapi.controllers;

import javax.validation.Valid;

import com.example.expenseitapi.entities.JwtResponse;
import com.example.expenseitapi.entities.LoginModel;
import com.example.expenseitapi.entities.User;
import com.example.expenseitapi.entities.UserModel;
import com.example.expenseitapi.security.CustomUserDetailsService;
import com.example.expenseitapi.services.UserService;
import com.example.expenseitapi.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private CustomUserDetailsService userDatailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginModel data) throws Exception {
        authenticate(data.getEmail(), data.getPassword());
        final UserDetails userDetails = userDatailsService.loadUserByUsername(data.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        //SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserModel uModel) {
        return new ResponseEntity<>(userService.createUser(uModel), HttpStatus.CREATED);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("User disabled");
        } catch(BadCredentialsException e) {
            throw new Exception("Bad credentials");
        }
    }

}
