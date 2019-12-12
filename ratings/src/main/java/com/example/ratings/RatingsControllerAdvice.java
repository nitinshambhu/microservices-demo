package com.example.ratings;

import com.example.ratings.exception.ResourceNotFoundException;
import com.example.ratings.model.Response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RatingsControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handleMovieNotFoundException(ResourceNotFoundException exception){
        return Response.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .statusMessage(exception.getMessage())
                .build();
    }
}
