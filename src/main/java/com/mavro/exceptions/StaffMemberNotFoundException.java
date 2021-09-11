package com.mavro.exceptions;

public class StaffMemberNotFoundException extends RuntimeException{

    public StaffMemberNotFoundException() {
    }

    public StaffMemberNotFoundException(String message) {
        super(message);
    }
}
