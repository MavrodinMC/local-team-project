package com.mavro.dto;

public class TournamentRequest {

    private String name;

    public TournamentRequest() {
    }

    public TournamentRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TournamentRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
