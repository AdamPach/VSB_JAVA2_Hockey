package com.adampach.hockey.mapper;

import com.adampach.hockey.dto.UpdateMatch;
import com.adampach.hockey.model.Match;

public class UpdateMatchMapper implements Mapper<UpdateMatch, Match> {
    @Override
    public Match map(UpdateMatch match) {
        Match newMatch = new Match();

        newMatch.setMatchDate(match.getMatchDate());
        newMatch.setDescription(match.getDescription());

        newMatch.setHomeTeamId(match.getHomeTeamId());
        newMatch.setHomeTeamScore(match.getHomeScore());

        newMatch.setAwayTeamId(match.getAwayTeamId());
        newMatch.setAwayTeamScore(match.getAwayScore());

        return newMatch;
    }
}
