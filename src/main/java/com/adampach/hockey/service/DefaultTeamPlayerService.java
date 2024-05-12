package com.adampach.hockey.service;

import com.adampach.hockey.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultTeamPlayerService implements TeamPlayerService{

    private final TeamService teamService;

    public DefaultTeamPlayerService(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public List<Player> getPlayersForTeam(int teamId) {
        return teamService.getTeamById(teamId).getPlayers();
    }
}
