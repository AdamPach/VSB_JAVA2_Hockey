package com.adampach.hockey.controller;

import com.adampach.hockey.dto.MatchDetail;
import com.adampach.hockey.dto.UpdateTeam;
import com.adampach.hockey.exception.EntityNotFoundException;
import com.adampach.hockey.exception.PlayerIsAlreadyInTheTeamException;
import com.adampach.hockey.exception.PlayerIsNotInTheTeam;
import com.adampach.hockey.model.Player;
import com.adampach.hockey.model.Team;
import com.adampach.hockey.service.TeamMatchService;
import com.adampach.hockey.service.TeamPlayerService;
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
    private final TeamPlayerService teamPlayerService;
    private final TeamMatchService teamMatchService;

    public TeamController(
            TeamService teamService,
            TeamPlayerService teamPlayerService,
            TeamMatchService teamMatchService) {
        this.teamService = teamService;
        this.teamPlayerService = teamPlayerService;
        this.teamMatchService = teamMatchService;
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
    public ResponseEntity<Team> createTeam(@RequestBody UpdateTeam team) {
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
    public ResponseEntity<Team> updateTeam(@PathVariable("teamId") int id, @RequestBody UpdateTeam team) {
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
            players = teamPlayerService.getPlayersForTeam(teamId);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(players);
    }

    @PostMapping("{teamId}/players/{playerId}")
    public ResponseEntity<Player> addPlayerToTeam(
            @PathVariable("teamId") int teamId,
            @PathVariable("playerId") int playerId) {

        try {
            teamPlayerService.addPlayerToTeam(teamId, playerId);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (PlayerIsAlreadyInTheTeamException e) {
            return ResponseEntity.status(409).build();
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{teamId}/players/{playerId}")
    public ResponseEntity<Player> removePlayerFromTeam(
            @PathVariable("teamId") int teamId,
            @PathVariable("playerId") int playerId){

        try {
            teamPlayerService.removePlayerFromTeam(teamId, playerId);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        catch (PlayerIsNotInTheTeam e) {
            return ResponseEntity.status(409).build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{teamId}/matches")
    public ResponseEntity<List<MatchDetail>> getTeamMatches(@PathVariable("teamId") int teamId)
    {
        List<MatchDetail> matches;

        try {
            matches = teamMatchService.getMatchesForTeam(teamId);
        }
        catch (EntityNotFoundException e)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(matches);
    }
}
