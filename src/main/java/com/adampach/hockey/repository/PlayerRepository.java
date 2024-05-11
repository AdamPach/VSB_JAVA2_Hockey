package com.adampach.hockey.repository;

import com.adampach.hockey.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
