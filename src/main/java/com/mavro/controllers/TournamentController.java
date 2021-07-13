package com.mavro.controllers;

import com.mavro.dto.GameDetails;
import com.mavro.dto.TournamentRequest;
import com.mavro.entities.Game;
import com.mavro.entities.Tournament;
import com.mavro.services.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tournament>> findAll() {
        return new ResponseEntity<>(tournamentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<Tournament> findOneById(@RequestParam("tournamentId") int tournamentId) {

        return new ResponseEntity<>(tournamentService.findOneById(tournamentId), HttpStatus.OK);
    }

    @GetMapping("/gameList/{tournamentId}")
    public ResponseEntity<List<Game>> getGamesList(@PathVariable("tournamentId") int tournamentId) {
        return new ResponseEntity<>(tournamentService.getGamesList(tournamentId), HttpStatus.OK);
    }


    @PostMapping(value = "/add")
    public ResponseEntity<Tournament> addTournament(@RequestBody TournamentRequest request) {
        return new ResponseEntity<>(tournamentService.addTournament(request), HttpStatus.CREATED);
    }

    @PostMapping("/game/{tournamentId}")
    public ResponseEntity<?> addGamesToATournament(@PathVariable("tournamentId") int tournamentId, @RequestBody GameDetails gameDetails) {
        return new ResponseEntity<>(tournamentService.addGamesToATournament(tournamentId, gameDetails), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Tournament> updateTournament(@RequestBody Tournament tournament) {
        return status(HttpStatus.OK).body(tournamentService.updateTournament(tournament));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTournamentById(@PathVariable("id") int id) {
         tournamentService.deleteTournamentById(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }
}
