package com.mavro.dto;

public class PlayerDetails {

    private String name;
    private String position;
    private String foot;
    private boolean isSenior;

    public PlayerDetails() {
    }

    public PlayerDetails(String name, String position, String foot, boolean isSenior) {
        this.name = name;
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
