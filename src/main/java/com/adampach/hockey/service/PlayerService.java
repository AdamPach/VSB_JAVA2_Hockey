package com.adampach.hockey.service;


import com.adampach.hockey.model.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getPlayers();
    Player getPlayer(int id);
    Player addPlayer(Player player);
    Player updatePlayer(int id, Player player);
    void deletePlayer(int id);
}
