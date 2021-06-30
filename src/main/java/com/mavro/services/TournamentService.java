package com.mavro.services;

import com.mavro.dto.GameDetails;
import com.mavro.dto.TournamentRequest;
import com.mavro.entities.Game;
import com.mavro.entities.Tournament;
import com.mavro.exceptions.TournamentNotFoundException;
import com.mavro.repositories.GameRepository;
import com.mavro.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final GameService gameService;
    private final GameRepository gameRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository, GameService gameService,
                             GameRepository gameRepository) {
        this.tournamentRepository = tournamentRepository;
        this.gameService = gameService;
        this.gameRepository = gameRepository;
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

    public Game addGamesToATournament(int tournamentId, GameDetails gameDetails) {
        Game game = gameService.addGame(gameDetails);
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException("Not found."));
        tournament.addGame(game);
        gameRepository.save(game);
        return game;
    }

    public Tournament updateTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Tournament findOneById(int tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() ->
                             new TournamentNotFoundException("Tournament not found."));
    }

    public List<Game> getGamesList(int tournamentId) {
      Tournament tournament = tournamentRepository.findById(tournamentId)
              .orElseThrow(() ->
                          new TournamentNotFoundException("Not found"));

      return tournament.getGames();
    }

    public void deleteTournamentById(int id) {
        tournamentRepository.deleteById(id);
    }
}
