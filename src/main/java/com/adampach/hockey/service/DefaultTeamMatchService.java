package com.adampach.hockey.service;

import com.adampach.hockey.dto.MatchDetail;
import com.adampach.hockey.mapper.MatchDetailMapper;
import com.adampach.hockey.model.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultTeamMatchService implements TeamMatchService {

    private final TeamService teamService;

    public DefaultTeamMatchService(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public List<MatchDetail> getMatchesForTeam(int teamId) {
        Team team = teamService.getTeamById(teamId);

        List<MatchDetail> matchDetails = new ArrayList<>();

        MatchDetailMapper mapper = new MatchDetailMapper();

        matchDetails.addAll(team.getHomeMatches().stream().map(mapper::map).toList());
        matchDetails.addAll(team.getAwayMatches().stream().map(mapper::map).toList());

        return matchDetails;
    }
}
