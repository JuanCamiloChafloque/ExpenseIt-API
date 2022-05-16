package com.example.expenseitapi.services;

import java.util.List;

import com.example.expenseitapi.entities.Expense;
import com.example.expenseitapi.repositories.ExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    @Override
    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }
    
}
