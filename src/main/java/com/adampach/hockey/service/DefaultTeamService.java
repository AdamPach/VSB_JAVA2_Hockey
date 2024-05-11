package com.adampach.hockey.service;

import com.adampach.hockey.exception.EntityNotFoundException;
import com.adampach.hockey.model.Team;
import com.adampach.hockey.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    @Override
    public Team getTeamById(int id) {
        return FindTeamById(id);
    }

    @Transactional
    @Override
    public Team createTeam(Team team) {

        try{
            teamRepository.save(team);
        }catch (DataIntegrityViolationException e)
        {
            logger.warn("Trying to save a existing team");
            throw e;
        }

        logger.info("Creating new team with name {} and id {} created", team.getName(), team.getId());
        return team;
    }

    @Transactional
    @Override
    public Team updateTeam(Team team) {
        Team oldTeam = FindTeamById(team.getId());

        oldTeam.setName(team.getName());
        oldTeam.setDescription(team.getDescription());
        oldTeam.setCity(team.getCity());
        oldTeam.setImageUrl(team.getImageUrl());
        oldTeam.setEstablishmentDate(team.getEstablishmentDate());

        return teamRepository.save(oldTeam);
    }

    @Transactional
    @Override
    public void deleteTeam(int id) {
        Team team = FindTeamById(id);

        teamRepository.delete(team);
    }

    private Team FindTeamById(int id) {
        Optional<Team> team = teamRepository.findById(id);

        if (team.isEmpty()) {
            throw new EntityNotFoundException("Team with id " + id + " not found");
        }

        return team.get();
    }
}
