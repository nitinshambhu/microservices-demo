package com.example.movie;


import com.example.movie.exception.MovieCreationFailedException;
import com.example.movie.exception.MovieNotFoundException;
import com.example.movie.model.MovieDTO;
import com.example.movie.model.Response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MovieControllerAdvice {


    @ResponseBody
    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Response<MovieDTO> handleMovieNotFoundException(MovieNotFoundException exception) {
        return Response.<MovieDTO>builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .statusMessage(exception.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(MovieCreationFailedException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Response<MovieDTO> handleMovieCreationFailedException(MovieCreationFailedException exception) {
        return Response.<MovieDTO>builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusMessage(exception.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected Response<MovieDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> details = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            details.add(objectError.getDefaultMessage());
        });

        return Response.<MovieDTO>builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusMessage(details.toString())
                .build();
    }
}
