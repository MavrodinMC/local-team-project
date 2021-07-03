package com.mavro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TournamentNotFoundException extends RuntimeException{

    public TournamentNotFoundException() {
    }

    public TournamentNotFoundException(String message) {
        super(message);
    }
}
