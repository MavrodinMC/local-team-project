package com.mavro.services;

import com.mavro.dto.PlayerDetails;
import com.mavro.entities.Player;
import com.mavro.exceptions.PlayerNotFoundException;
import com.mavro.repositories.PlayerRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Query("SELECT p FROM Player p WHERE p.seniority = ?")
    public List<Player> getAllSeniors() {
        return playerRepository.findAll()
                .stream()
                .filter(Player::isSenior)
                .collect(Collectors.toList());
    }

    @Query("SELECT p FROM Player p WHERE p.seniority = ?")
    public List<Player> getAllJuniors() {
        return playerRepository.findAll()
                .stream()
                .filter(p -> !p.isSenior())
                .collect(Collectors.toList());
    }

    public Player addPlayer(PlayerDetails playerDetails) {

        Player player = new Player();
        player.setName(playerDetails.getName());
        player.setGoals(playerDetails.getGoals());
        player.setShirtNumber(playerDetails.getShirtNumber());
        player.setDateOfBirth(playerDetails.getDateOfBirth());
        player.setPosition(playerDetails.getPosition());
        player.setFoot(playerDetails.getFoot());
        player.setSenior(playerDetails.isSenior());
        playerRepository.save(player);
        return player;
    }

    public Player updatePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player findOneById(int playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found."));
    }

    public void deletePlayerById(int id){
        playerRepository.deleteById(id);
    }

}
