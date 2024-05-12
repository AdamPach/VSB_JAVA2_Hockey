package com.adampach.hockey.service;

import com.adampach.hockey.exception.PlayerIsAlreadyInTheTeamException;
import com.adampach.hockey.exception.PlayerIsNotInTheTeam;
import com.adampach.hockey.model.Player;
import com.adampach.hockey.model.Team;
import com.adampach.hockey.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultTeamPlayerService implements TeamPlayerService{

    private final TeamService teamService;
    private final PlayerService playerService;
    private final PlayerRepository playerRepository;

    public DefaultTeamPlayerService(TeamService teamService,
                                    PlayerService playerService,
                                    PlayerRepository playerRepository) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> getPlayersForTeam(int teamId) {
        return teamService.getTeamById(teamId).getPlayers();
    }

    @Override
    @Transactional
    public void addPlayerToTeam(int teamId, int playerId) {
        Player player = playerService.getPlayer(playerId);

        if(player.getTeamId() != null)
        {
            throw new PlayerIsAlreadyInTheTeamException();
        }

        Team team = teamService.getTeamById(teamId);

        player.setTeam(team);

        playerRepository.save(player);
    }

    @Override
    public void removePlayerFromTeam(int teamId, int playerId) {
        Player player = playerService.getPlayer(playerId);

        if(player.getTeamId() == null || player.getTeamId() != teamId)
        {
            throw new PlayerIsNotInTheTeam();
        }

        player.setTeam(null);

        playerRepository.save(player);
    }
}
