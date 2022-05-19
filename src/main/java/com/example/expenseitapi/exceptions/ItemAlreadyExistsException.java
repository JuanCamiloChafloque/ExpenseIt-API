package com.example.expenseitapi.exceptions;

public class ItemAlreadyExistsException extends RuntimeException {
    
    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}
