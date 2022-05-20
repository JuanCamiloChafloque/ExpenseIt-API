package com.example.expenseitapi.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.example.expenseitapi.entities.Expense;
import com.example.expenseitapi.exceptions.ResourceNotFoundException;
import com.example.expenseitapi.repositories.ExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        return repository.findByUserId(userService.getLoggedInUser().getId(), page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = repository.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
        if(expense.isPresent()) {
            return expense.get();
        }
        throw new ResourceNotFoundException("Expense not found for id: " + id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        Expense expense = getExpenseById(id);
        repository.delete(expense);
    }

    @Override
    public Expense createExpense(Expense expense) {
        expense.setUser(userService.getLoggedInUser());
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

    @Override
    public List<Expense> findExpenseByCategory(String category, Pageable page) {
        return repository.findByUserIdAndCategory(userService.getLoggedInUser().getId(), category, page).toList();        
    }

    @Override
    public List<Expense> findExpenseByNameContaining(String keyword, Pageable page) {
        return repository.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(), keyword, page).toList();
    }

    @Override
    public List<Expense> findExpenseByDateBetween(Date startDate, Date endDate, Pageable page) {
        if(startDate == null) {
            startDate = new Date(0);
        }

        if(endDate == null) {
            endDate = new Date(System.currentTimeMillis());
        }

        return repository.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(), startDate, endDate, page).toList();
    }
    
}
