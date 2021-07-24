package com.mavro.controllers;

import com.mavro.entities.Game;
import com.mavro.entities.Player;
import com.mavro.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Game>> getAllGames() {
        return new ResponseEntity<>(gameService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/players/all/{gameId}")
    public ResponseEntity<List<Player>> getAllPlayersInAGame(@PathVariable("gameId") int gameId) {

        return new ResponseEntity<>(gameService.getAllPlayersInAGame(gameId), HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<Game> findOneById(@RequestParam("gameId") int gameId) {

        return new ResponseEntity<>(gameService.findOneById(gameId), HttpStatus.OK);
    }

    @PutMapping("/update/{tournamentId}")
    public ResponseEntity<Game> updateGame(@PathVariable("tournamentId") int tournamentId, @RequestBody Game game) {

        gameService.updateGameInATournament(tournamentId, game);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/players/{gameId}")
    public ResponseEntity<Game> addPlayersToAGame(@PathVariable("gameId") int gameId, @RequestBody List<Player> players) {
        return new ResponseEntity<>(gameService.addPlayersToAGame(gameId, players), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGameById(@PathVariable("id") int id) {
        gameService.deleteGameById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/players/delete/{gameId}/{playerId}")
    public ResponseEntity<?> deleteAPlayerFromGameList(@PathVariable("gameId") int gameId, @PathVariable("playerId") int playerId){

        gameService.deleteAPlayerFromGameList(gameId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
