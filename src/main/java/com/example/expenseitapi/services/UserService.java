package com.example.expenseitapi.services;

import com.example.expenseitapi.entities.User;
import com.example.expenseitapi.entities.UserModel;

public interface UserService {

    User createUser(UserModel user);
}
