package com.mavro.exceptions;

public class EmptyInputException extends RuntimeException {

    public EmptyInputException() {
    }

    public EmptyInputException(String message) {
        super(message);
    }
}
