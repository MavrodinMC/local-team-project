package com.mavro.services;

import com.mavro.dto.GameDetails;
import com.mavro.entities.Game;
import com.mavro.entities.Player;
import com.mavro.entities.Tournament;
import com.mavro.exceptions.GameNotFoundException;
import com.mavro.exceptions.PlayerNotFoundException;
import com.mavro.exceptions.TournamentNotFoundException;
import com.mavro.repositories.GameRepository;
import com.mavro.repositories.PlayerRepository;
import com.mavro.repositories.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final TournamentRepository tournamentRepository;


    public GameService(GameRepository gameRepository, PlayerRepository playerRepository, TournamentRepository tournamentRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.tournamentRepository = tournamentRepository;
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

    public Game addPlayersToAGame(int gameId, List<Player> players) {

        Game game = gameRepository.findById(gameId)
                .orElseThrow(()-> new GameNotFoundException("The requested game was not found."));

        for(Player player : players) {
            game.addPlayer(player);
        }

        return gameRepository.save(game);

    }

    public List<Player> getAllPlayersInAGame(int gameId) {

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException("The requested game was not found."));

        return new ArrayList<>(game.getPlayers());
    }

    public List<Player> getAllPlayers(int gameId) {
        Game game = gameRepository.findById((gameId))
                .orElseThrow(() -> new GameNotFoundException("Game was not found."));
        List<Player> playerList = playerRepository.findAll();
        playerList.removeIf(player -> game.getPlayers().contains(player));
        return playerList;
    }

    public void updateGameInATournament(int tournamentId, Game game) {

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException("The requested tournament was not found."));
        game.setTournament(tournament);
        gameRepository.save(game);
    }

    public Game findOneById(int gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException("The requested game was not found."));
    }

    public void deleteGameById(int id) {
        gameRepository.deleteById(id);
    }

    public void deleteAPlayerFromGameList(int gameId, int playerId) {

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException("The requested game was not found."));

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("The requested player was not found."));

       List<Player> playerList = new ArrayList<>(game.getPlayers());

       for(Player playerToDelete : playerList) {
           if(playerToDelete.getId().equals(player.getId())) {
               game.removePlayer(playerToDelete);
           }
       }

        gameRepository.save(game);
    }
}
