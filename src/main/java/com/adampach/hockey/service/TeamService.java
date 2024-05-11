package com.adampach.hockey.service;

import com.adampach.hockey.model.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();
    Team createTeam(Team team);
}
