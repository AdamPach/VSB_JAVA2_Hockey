package com.adampach.hockey.service;

import com.adampach.hockey.dto.MatchDetail;

import java.util.List;

public interface MatchService {
    List<MatchDetail> getAllMatches();
    MatchDetail getMatch(int matchId);
}
