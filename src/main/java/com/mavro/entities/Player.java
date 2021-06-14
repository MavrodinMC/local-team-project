package com.mavro.entities;

import javax.persistence.*;

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

    @Column(name = "game_id", insertable = false, updatable = false)
    private Integer gameId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

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

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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
