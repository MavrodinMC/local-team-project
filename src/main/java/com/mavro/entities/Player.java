package com.mavro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String position;
    private String foot;

    @Column(name = "seniority")
    private boolean isSenior;

    @JsonIgnore
    @ManyToMany(mappedBy = "players")
    private Set<Game> games = new HashSet<>();

    public Player() {
    }

    public Player(String name, String position, String foot, boolean isSenior) {
        this.name = name;
        this.position = position;
        this.foot = foot;
        this.isSenior = isSenior;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", foot='" + foot + '\'' +
                ", isSenior=" + isSenior +
                '}';
    }
}
