package com.example.expenseitapi.services;

import com.example.expenseitapi.entities.User;
import com.example.expenseitapi.entities.UserModel;
import com.example.expenseitapi.exceptions.ItemAlreadyExistsException;
import com.example.expenseitapi.exceptions.ResourceNotFoundException;
import com.example.expenseitapi.repositories.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User createUser(UserModel uModel) {
        if(repository.existsByEmail(uModel.getEmail())) {
            throw new ItemAlreadyExistsException("Uer is already registered with email " + uModel.getEmail());
        }
        User user = new User();
        BeanUtils.copyProperties(uModel, user);
        return repository.save(user);
    }

    @Override
    public User getCurrentUser(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }
    
}
