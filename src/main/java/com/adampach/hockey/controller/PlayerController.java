package com.adampach.hockey.controller;

import com.adampach.hockey.dto.UpdatePlayer;
import com.adampach.hockey.exception.EntityNotFoundException;
import com.adampach.hockey.model.Player;
import com.adampach.hockey.service.PlayerService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers() {
        return ResponseEntity.ok(playerService.getPlayers());
    }

    @GetMapping("{playerId}")
    public ResponseEntity<Player> getPlayer(@PathVariable("playerId") int playerId) {
        Player player;

        try {
            player = playerService.getPlayer(playerId);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(player);
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody UpdatePlayer player) {
        Player createdPlayer;

        try {
            createdPlayer = playerService.addPlayer(player);
        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.created(URI.create("players/" + createdPlayer.getId())).body(createdPlayer);
    }

    @PutMapping("{playerId}")
    public ResponseEntity<Player> updatePlayer(
            @PathVariable("playerId") int playerId,
            @RequestBody UpdatePlayer player) {
        Player updatedPlayer;

        try {
            updatedPlayer = playerService.updatePlayer(playerId, player);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedPlayer);
    }

    @DeleteMapping("{playerId}")
    public ResponseEntity<Player> deletePlayer(@PathVariable("playerId") int playerId) {
        try {
            playerService.deletePlayer(playerId);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
