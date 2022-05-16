package com.example.expenseitapi.services;

import com.example.expenseitapi.entities.Expense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {
    
    Page<Expense> getAllExpenses(Pageable page);
    Expense getExpenseById(Long id);
    void deleteExpenseById(Long id);
    Expense createExpense(Expense expense);
    Expense updateExpenseById(Long id, Expense expense);
}
