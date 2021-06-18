package com.mavro.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(
            mappedBy = "tournament",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<Game> games = new ArrayList<>();

    public Tournament() {
    }

    public Tournament(String name) {
        this.name = name;
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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void addGame(Game game) {
        games.add(game);
        game.setTournament(this);
    }

    public void removeGame(Game game) {
        games.remove(game);
        game.setTournament(null);
    }
}
