package com.example.users.controller;


import com.example.users.exception.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String onResourceNotFoundException(ResourceNotFoundException exception){
        return exception.getMessage();
    }
}
