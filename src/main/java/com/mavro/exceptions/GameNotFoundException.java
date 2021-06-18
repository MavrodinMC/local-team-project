package com.mavro.exceptions;

public class GameNotFoundException extends RuntimeException{

    public GameNotFoundException() {
    }

    public GameNotFoundException(String message) {
        super(message);
    }
}
