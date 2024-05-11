package com.adampach.hockey.service;

import com.adampach.hockey.model.Team;
import com.adampach.hockey.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultTeamService implements TeamService {

    private final TeamRepository teamRepository;
    private final Logger logger = LoggerFactory.getLogger(DefaultTeamService.class);


    public DefaultTeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    @Override
    public List<Team> getAllTeams() {
        logger.info("Getting all teams");
        return teamRepository.findAll();
    }
}
