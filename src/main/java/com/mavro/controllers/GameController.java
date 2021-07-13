package com.mavro.controllers;

import com.mavro.entities.Game;
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

    @GetMapping("/view")
    public ResponseEntity<Game> findOneById(@RequestParam("gameId") int gameId) {

        return new ResponseEntity<>(gameService.findOneById(gameId), HttpStatus.OK);
    }

    @PutMapping("/update/{tournamentId}")
    public ResponseEntity<Game> updateGame(@PathVariable("tournamentId") int tournamentId, @RequestBody Game game) {

        gameService.updateGameInATournament(tournamentId, game);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGameById(@PathVariable("id") int id) {
        gameService.deleteGameById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
