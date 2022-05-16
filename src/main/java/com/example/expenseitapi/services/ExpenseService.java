package com.example.expenseitapi.services;

import java.util.List;
import com.example.expenseitapi.entities.Expense;

public interface ExpenseService {
    
    List<Expense> getAllExpenses();
    Expense getExpenseById(Long id);
    void deleteExpenseById(Long id);
    Expense createExpense(Expense expense);
    Expense updateExpenseById(Long id, Expense expense);
}
