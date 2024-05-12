package com.adampach.hockey.mapper;

import com.adampach.hockey.dto.MatchDetail;
import com.adampach.hockey.model.Match;

public class MatchDetailMapper implements Mapper<Match, MatchDetail> {
    @Override
    public MatchDetail map(Match match) {
        MatchDetail matchDetail = new MatchDetail();

        matchDetail.setId(match.getId());

        matchDetail.setHomeTeamName(match.getHomeTeam().getName());
        matchDetail.setHomeTeamId(match.getHomeTeamId());
        matchDetail.setHomeScore(match.getHomeTeamScore());

        matchDetail.setAwayTeamName(match.getAwayTeam().getName());
        matchDetail.setAwayTeamId(match.getAwayTeamId());
        matchDetail.setAwayScore(match.getAwayTeamScore());

        matchDetail.setMatchDate(match.getMatchDate());
        matchDetail.setDescription(match.getDescription());

        return matchDetail;
    }
}
