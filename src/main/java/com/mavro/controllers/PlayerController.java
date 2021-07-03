package com.mavro.controllers;

import com.mavro.dto.PlayerDetails;
import com.mavro.entities.Player;
import com.mavro.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers(@RequestParam(value = "isSenior") boolean isSenior) {
        if (isSenior) {
            return status(HttpStatus.OK).body(service.getAllSeniors());
        } else {
            return status(HttpStatus.OK).body(service.getAllJuniors());
        }
    }

    @GetMapping("/view")
    public ResponseEntity<Player> getOneById(@RequestParam("playerId") int playerId) {
        return new ResponseEntity<>(service.findOneById(playerId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Player> addPlayer(@RequestBody PlayerDetails playerDetails) {
        Player player = service.addPlayer(playerDetails);

        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {

        return status(HttpStatus.OK).body(service.updatePlayer(player));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSeniorById(@PathVariable("id") int id) {
        service.deletePlayerById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
