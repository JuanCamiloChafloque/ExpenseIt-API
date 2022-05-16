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

    @Override
    public Expense createExpense(Expense expense) {
        return repository.save(expense);
    }

    @Override
    public Expense updateExpenseById(Long id, Expense expense) {
        Expense existingExpense = getExpenseById(id);
        existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
        existingExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
        return repository.save(existingExpense);
    }
    
}
