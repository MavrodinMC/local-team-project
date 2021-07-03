package com.mavro.controllers;

import com.mavro.dto.GameDetails;
import com.mavro.entities.Game;
import com.mavro.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

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

    @PostMapping("/add")
    public ResponseEntity<Game> addGame(@RequestBody GameDetails gameDetails) {
        Game game = gameService.addGame(gameDetails);

        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {

        return status(HttpStatus.OK).body(gameService.updateGame(game));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGameById(@PathVariable("id") int id) {
        gameService.deleteGameById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
