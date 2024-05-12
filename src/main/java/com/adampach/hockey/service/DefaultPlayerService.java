package com.adampach.hockey.service;

import com.adampach.hockey.dto.UpdatePlayer;
import com.adampach.hockey.exception.EntityNotFoundException;
import com.adampach.hockey.model.Player;
import com.adampach.hockey.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DefaultPlayerService implements PlayerService {

    private final PlayerRepository playerRepository;
    private final Logger logger = LoggerFactory.getLogger(DefaultPlayerService.class);

    public DefaultPlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    @Transactional
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @Override
    @Transactional
    public Player getPlayer(int id) {
        return getPlayerById(id);
    }

    @Override
    @Transactional
    public Player addPlayer(UpdatePlayer player) {
        Player newPlayer;

        try{
            newPlayer = playerRepository.save(player.getPlayer());
        }catch (DataIntegrityViolationException e)
        {
            logger.warn("Trying to save a existing player");
            throw e;
        }

        logger.info("New player with id {} added", newPlayer.getId());

        return newPlayer;
    }

    @Override
    @Transactional
    public Player updatePlayer(int id, UpdatePlayer player) {
        Player oldPlayer = getPlayerById(id);

        oldPlayer.setBirthDate(player.getBirthDate());
        oldPlayer.setFirstName(player.getFirstName());
        oldPlayer.setLastName(player.getLastName());
        oldPlayer.setEmail(player.getEmail());

        logger.info("Player with id {} updated", oldPlayer.getId());

        return playerRepository.save(oldPlayer);
    }

    @Override
    @Transactional
    public void deletePlayer(int id) {
        Player player = getPlayerById(id);

        logger.info("Deleting player with id {}", id);

        playerRepository.delete(player);
    }

    private Player getPlayerById(int id) {
        Optional<Player> player = playerRepository.findById(id);

        if (player.isEmpty()) {
            throw new EntityNotFoundException("No player found with id " + id);
        }

        return player.get();
    }
}
