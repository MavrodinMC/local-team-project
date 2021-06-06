package com.mavro.dto;

public class RegistrationRequest {

    private String name;
    private String password;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
