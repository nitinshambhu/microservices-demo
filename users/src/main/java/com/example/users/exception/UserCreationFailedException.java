package com.example.users.exception;

import java.util.function.Supplier;

public class UserCreationFailedException extends Exception {

    public UserCreationFailedException(String message) {
        super(message);
    }
}
