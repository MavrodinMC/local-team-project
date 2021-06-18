package com.mavro.exceptions;

public class TournamentNotFoundException extends RuntimeException{

    public TournamentNotFoundException() {
    }

    public TournamentNotFoundException(String message) {
        super(message);
    }
}
