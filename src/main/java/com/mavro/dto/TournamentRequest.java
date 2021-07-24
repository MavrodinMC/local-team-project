package com.mavro.dto;

public class TournamentRequest {

    private String name;
    private boolean isActive = true;

    public TournamentRequest() {
    }

    public TournamentRequest(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
