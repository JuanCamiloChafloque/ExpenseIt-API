package com.example.expenseitapi.services;

import java.sql.Date;
import java.util.List;

import com.example.expenseitapi.entities.Expense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {
    
    Page<Expense> getAllExpenses(Pageable page);
    Expense getExpenseById(Long id);
    void deleteExpenseById(Long id);
    Expense createExpense(Expense expense);
    Expense updateExpenseById(Long id, Expense expense);
    List<Expense> findExpenseByCategory(String category, Pageable page);
    List<Expense> findExpenseByNameContaining(String keyword, Pageable page);
    List<Expense> findExpenseByDateBetween(Date startDate, Date endDate, Pageable page);

}
