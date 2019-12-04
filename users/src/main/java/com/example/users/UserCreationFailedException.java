package com.example.users;

import java.util.function.Supplier;

public class UserCreationFailedException extends Exception {

    public UserCreationFailedException(String message) {
        super(message);
    }
}
