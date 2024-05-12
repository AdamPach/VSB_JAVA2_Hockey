package com.adampach.hockey.service;

import com.adampach.hockey.model.Player;

import java.util.List;

public interface TeamPlayerService {
    List<Player> getPlayersForTeam(int teamId);

    void addPlayerToTeam(int teamId, int playerId);

    void removePlayerFromTeam(int teamId, int playerId);
}
