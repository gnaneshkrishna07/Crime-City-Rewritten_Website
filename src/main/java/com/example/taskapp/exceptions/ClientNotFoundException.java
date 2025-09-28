package com.example.taskapp.exceptions;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String message) {
        super(message);
    }

}
