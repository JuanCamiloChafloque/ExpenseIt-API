package com.example.expenseitapi.exceptions;

import java.util.Date;

import com.example.expenseitapi.entities.ErrorObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ResourceNotFoundException ex, WebRequest req) {
        ErrorObject eo = new ErrorObject();
        eo.setStatusCode(HttpStatus.NOT_FOUND.value());
        eo.setMessage(ex.getMessage());
        eo.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(eo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest req) {
        ErrorObject eo = new ErrorObject();
        eo.setStatusCode(HttpStatus.BAD_REQUEST.value());
        eo.setMessage(ex.getMessage());
        eo.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(eo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGeneralException(Exception ex, WebRequest req) {
        ErrorObject eo = new ErrorObject();
        eo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        eo.setMessage(ex.getMessage());
        eo.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(eo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
