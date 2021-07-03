package com.mavro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GameNotFoundException extends RuntimeException{

    public GameNotFoundException() {
    }

    public GameNotFoundException(String message) {
        super(message);
    }
}
