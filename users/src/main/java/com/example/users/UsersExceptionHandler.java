package com.example.users;

import com.example.users.exception.UserCreationFailedException;
import com.example.users.exception.UserNotFoundException;
import com.example.users.model.Response;
import com.example.users.model.UserDTO;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class UsersExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Response<UserDTO> handleUserNotFoundException(UserNotFoundException exception) {
        return Response.<UserDTO>builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .statusMessage(exception.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(UserCreationFailedException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Response<UserDTO> handleUserCreationFailedException(UserCreationFailedException exception) {
        return Response.<UserDTO>builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusMessage(exception.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected Response<UserDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> details = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            details.add(objectError.getDefaultMessage());
        });

        return Response.<UserDTO>builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusMessage(details.toString())
                .build();
    }
}
