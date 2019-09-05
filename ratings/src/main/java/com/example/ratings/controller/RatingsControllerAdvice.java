package com.example.ratings.controller;

import com.example.ratings.exception.ResourceNotFoundException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RatingsControllerAdvice {

    @ExceptionHandler
    public String onResourceNotFoundException(ResourceNotFoundException exception){
        return exception.getMessage();
    }
}
