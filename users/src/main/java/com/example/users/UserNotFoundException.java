package com.example.users;

import java.util.function.Supplier;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }
}
