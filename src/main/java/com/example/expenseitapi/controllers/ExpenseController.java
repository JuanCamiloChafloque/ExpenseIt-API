package com.example.expenseitapi.controllers;

import java.util.List;

import com.example.expenseitapi.services.ExpenseService;
import com.example.expenseitapi.entities.Expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    
    @GetMapping("/expenses")
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable("id") Long id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/expenses/{id}")
    public String deleteExpenseById(@PathVariable("id") Long id) {
        expenseService.deleteExpenseById(id);
        return "Expense deleted successfully";
    }
}
