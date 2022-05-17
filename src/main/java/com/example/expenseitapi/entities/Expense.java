package com.example.expenseitapi.entities;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "expenses")
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expense_name")
    @NotBlank(message = "Expense name must not be BLANK")
    @Size(min = 3, message = "Expense name must be at least 3 characters")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "expense_amount")
    @NotNull(message = "Expense amount must not be NULL")
    private BigDecimal amount;

    @Column(name = "category")
    @NotBlank(message = "Expense category must not be BLANK")
    private String category;
    
    @Column(name = "date")
    @NotNull(message = "Expense date must not be NULL")
    private Date date;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }

}
