package com.example.expenseitapi.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.expenseitapi.entities.ErrorObject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

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

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<ErrorObject> handleItemAlreadyExistsException(ItemAlreadyExistsException ex, WebRequest req) {
        ErrorObject eo = new ErrorObject();
        eo.setStatusCode(HttpStatus.CONFLICT.value());
        eo.setMessage(ex.getMessage());
        eo.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(eo, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        
        body.put("timestamp", new Date());
        body.put("statusCode", HttpStatus.BAD_REQUEST.value());
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        body.put("messages", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
