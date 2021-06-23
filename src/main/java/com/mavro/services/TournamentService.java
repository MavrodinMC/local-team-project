package com.mavro.services;

import com.mavro.dto.TournamentRequest;
import com.mavro.entities.Tournament;
import com.mavro.exceptions.TournamentNotFoundException;
import com.mavro.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    public Tournament addTournament(TournamentRequest request) {
        Tournament tournament = new Tournament();
        tournament.setName(request.getName());
        tournamentRepository.save(tournament);
        return tournament;
    }

    public Tournament updateTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Tournament findOneById(int tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() ->
                        new TournamentNotFoundException("Tournament not found."));
    }

    public void deleteTournamentById(int id) {
        tournamentRepository.deleteById(id);
    }
}
