package com.example.taskapp.exceptions;

public class TimelineNotFoundException extends RuntimeException {
    public TimelineNotFoundException(String message) {
        super(message);
    }
}
