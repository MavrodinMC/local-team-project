package com.mavro.controllers;

import com.mavro.dto.PlayerDetails;
import com.mavro.entities.Player;
import com.mavro.services.GameService;
import com.mavro.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;
    private final GameService gameService;

    public PlayerController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers(@RequestParam(value = "isSenior") boolean isSenior) {
        if (isSenior) {
            return status(HttpStatus.OK).body(playerService.getAllSeniors());
        } else {
            return status(HttpStatus.OK).body(playerService.getAllJuniors());
        }
    }

    @GetMapping("/view")
    public ResponseEntity<Player> getOneById(@RequestParam("playerId") int playerId) {
        return new ResponseEntity<>(playerService.findOneById(playerId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Player> addPlayer(@RequestBody PlayerDetails playerDetails) {
        Player player = playerService.addPlayer(playerDetails);

        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {

        return status(HttpStatus.OK).body(playerService.updatePlayer(player));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlayerById(@PathVariable("id") int id) {
        playerService.deletePlayerById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
