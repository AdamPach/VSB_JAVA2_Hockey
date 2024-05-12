package com.adampach.hockey.service;

import com.adampach.hockey.dto.UpdateTeam;
import com.adampach.hockey.model.Player;
import com.adampach.hockey.model.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();
    Team getTeamById(int id);
    Team createTeam(UpdateTeam team);
    Team updateTeam(int id, UpdateTeam team);
    void deleteTeam(int id);

    List<Player> getPlayersForTeam(int teamId);
}
