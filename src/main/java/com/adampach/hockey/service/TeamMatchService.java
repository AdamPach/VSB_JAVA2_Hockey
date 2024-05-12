package com.adampach.hockey.service;

import com.adampach.hockey.dto.MatchDetail;

import java.util.List;

public interface TeamMatchService {
    List<MatchDetail> getMatchesForTeam(int teamId);
}
