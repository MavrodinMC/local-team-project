package com.mavro.services;

import com.mavro.dto.GameDetails;
import com.mavro.entities.Game;
import com.mavro.entities.Player;
import com.mavro.exceptions.UserNotFoundException;
import com.mavro.repositories.GameRepository;
import com.mavro.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game addGame(GameDetails gameDetails) {

        Game game = new Game();
        game.setDate(gameDetails.getDate());
        game.setLocation(gameDetails.getLocation());
        game.setHomeTeam(gameDetails.getHomeTeam());
        game.setAwayTeam(gameDetails.getAwayTeam());
        game.setReferee(gameDetails.getReferee());
        game.setScore(gameDetails.getScore());

        return gameRepository.save(game);
    }

    public void addPlayersToAGame(int gameId, int playerId) {

        Game game = gameRepository.findById(gameId)
                .orElseThrow(()-> new UserNotFoundException("Not found."));

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new UserNotFoundException("Not found."));

        game.addPlayer(player);
        gameRepository.save(game);

    }

    public Game updateGame(Game game) {
        return gameRepository.save(game);
    }

    public Game findOneById(int id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Not found."));
    }

    public void deleteGameById(int id) {
        gameRepository.deleteById(id);
    }
}
