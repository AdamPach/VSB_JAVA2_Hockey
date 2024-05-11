package com.adampach.hockey.service;

import com.adampach.hockey.model.Player;
import com.adampach.hockey.model.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();
    Team getTeamById(int id);
    Team createTeam(Team team);
    Team updateTeam(int id, Team team);
    void deleteTeam(int id);

    List<Player> getPlayersForTeam(int teamId);
}
