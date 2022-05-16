package com.example.expenseitapi.services;

import java.util.List;
import com.example.expenseitapi.entities.Expense;

public interface ExpenseService {
    
    List<Expense> getAllExpenses();
}
