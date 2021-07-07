package com.mavro.dto;

import java.time.LocalDate;

public class PlayerDetails {

    private String name;
    private int goals = 0;
    private int shirtNumber;
    private LocalDate dateOfBirth;
    private String position;
    private String foot;
    private boolean isSenior;

    public PlayerDetails() {
    }

    public PlayerDetails(String name, int goals, int shirtNumber, LocalDate dateOfBirth, String position, String foot, boolean isSenior) {
        this.name = name;
        this.goals = goals;
        this.shirtNumber = shirtNumber;
        this.dateOfBirth = dateOfBirth;
        this.position = position;
        this.foot = foot;
        this.isSenior = isSenior;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFoot() {
        return foot;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }

    public boolean isSenior() {
        return isSenior;
    }

    public void setSenior(boolean senior) {
        isSenior = senior;
    }
}
