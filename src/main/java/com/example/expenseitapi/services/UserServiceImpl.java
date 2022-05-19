package com.example.expenseitapi.services;

import com.example.expenseitapi.entities.User;
import com.example.expenseitapi.entities.UserModel;
import com.example.expenseitapi.exceptions.ItemAlreadyExistsException;
import com.example.expenseitapi.exceptions.ResourceNotFoundException;
import com.example.expenseitapi.repositories.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public User createUser(UserModel uModel) {
        if(repository.existsByEmail(uModel.getEmail())) {
            throw new ItemAlreadyExistsException("User is already registered with email " + uModel.getEmail());
        }
        User user = new User();
        BeanUtils.copyProperties(uModel, user);
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User getCurrentUser(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public User updateCurrentUser(User user, Long id) {
        User current = getCurrentUser(id);
        current.setName(user.getName() != null ? user.getName() : current.getName());
        current.setEmail(user.getEmail() != null ? user.getEmail() : current.getEmail());
        current.setPassword(user.getPassword() != null ? bcryptEncoder.encode(user.getPassword()) : current.getPassword());
        current.setAge(user.getAge() != null ? user.getAge() : current.getAge());
        return repository.save(current);
    }

    @Override
    public void deleteCurrentUser(Long id) {
        User user = getCurrentUser(id);
        repository.delete(user);
    }
    
}
