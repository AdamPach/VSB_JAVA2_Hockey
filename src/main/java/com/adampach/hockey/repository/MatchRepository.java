package com.adampach.hockey.repository;

import com.adampach.hockey.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Integer> {}
