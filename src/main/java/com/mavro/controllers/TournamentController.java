package com.mavro.controllers;

import com.mavro.dto.TournamentRequest;
import com.mavro.entities.Tournament;
import com.mavro.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tournament>> findAll() {
        return new ResponseEntity<>(tournamentService.findAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Tournament> findOneById(@RequestParam("tournamentId") int tournamentId) {

        return new ResponseEntity<>(tournamentService.findOneById(tournamentId), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Tournament> addTournament(@RequestBody TournamentRequest request) {
        return new ResponseEntity<>(tournamentService.addTournament(request), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Tournament> updateTournament(@RequestBody Tournament tournament) {
        return new ResponseEntity<>(tournamentService.updateTournament(tournament), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTournamentById(@PathVariable("id") int id) {
         tournamentService.deleteTournamentById(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }
}
