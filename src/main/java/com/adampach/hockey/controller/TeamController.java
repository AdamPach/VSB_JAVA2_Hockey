package com.adampach.hockey.controller;

import com.adampach.hockey.exception.EntityNotFoundException;
import com.adampach.hockey.model.Player;
import com.adampach.hockey.model.Team;
import com.adampach.hockey.service.TeamService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable("teamId") int teamId) {
        Team team;

        try {
            team = teamService.getTeamById(teamId);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(team);
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team createdTeam;

        try{
            createdTeam = teamService.createTeam(team);
        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.created(URI.create("teams/" + createdTeam.getId())).body(createdTeam);
    }

    @PutMapping("{teamId}")
    public ResponseEntity<Team> updateTeam(@PathVariable("teamId") int id, @RequestBody Team team) {
        Team updatedTeam;

        try {
            updatedTeam = teamService.updateTeam(id, team);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("{teamId}")
    public ResponseEntity<Team> deleteTeam(@PathVariable("teamId") int id) {

        try {
            teamService.deleteTeam(id);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{teamId}/players")
    public ResponseEntity<List<Player>> getTeamPlayers(@PathVariable("teamId") int teamId) {
        List<Player> players;

        try{
            players = teamService.getPlayersForTeam(teamId);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(players);
    }
}
