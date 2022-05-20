package com.example.expenseitapi.repositories;

import java.sql.Date;
import java.util.Optional;

import com.example.expenseitapi.entities.Expense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    //SELECT * FROM expenses WHERE category = ?
    Page<Expense> findByCategory(String category, Pageable page);

    //SELECT * FROM expenses WHERE name LIKE '%keyword%'
    Page<Expense> findByNameContaining(String keyword, Pageable page);

    //SELECT * FROM expenses WHERE date BETWEEN 'startDate' AND 'endDate'
    Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);

    //SELECT * FROM expenses WHERE user_id = ?
    Page<Expense> findByUserId(Long userId, Pageable page);

    //SELECT * FROM expenses WHERE user_id = ? AND id = ?
    Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);
}
