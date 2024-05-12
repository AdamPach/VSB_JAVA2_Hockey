package com.adampach.hockey.service;

import com.adampach.hockey.dto.MatchDetail;
import com.adampach.hockey.dto.UpdateMatch;

import java.util.List;

public interface MatchService {
    List<MatchDetail> getAllMatches();
    MatchDetail getMatch(int matchId);
    MatchDetail addMatch(UpdateMatch match);
    MatchDetail updateMatch(int matchId, UpdateMatch match);
    void deleteMatch(int matchId);
}
