package com.adampach.hockey.service;


import com.adampach.hockey.dto.UpdatePlayer;
import com.adampach.hockey.model.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getPlayers();
    Player getPlayer(int id);
    Player addPlayer(UpdatePlayer player);
    Player updatePlayer(int id, UpdatePlayer player);
    void deletePlayer(int id);
}
