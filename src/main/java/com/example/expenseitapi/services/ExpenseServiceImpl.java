package com.example.expenseitapi.services;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = repository.findById(id);
        if(expense.isPresent()) {
            return expense.get();
        }
        throw new RuntimeException("Expense is not found for id " + id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        repository.deleteById(id);
    }
    
}
