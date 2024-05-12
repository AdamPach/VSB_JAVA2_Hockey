package com.adampach.hockey.controller;

import com.adampach.hockey.dto.MatchDetail;
import com.adampach.hockey.dto.UpdateMatch;
import com.adampach.hockey.exception.EntityNotFoundException;
import com.adampach.hockey.service.MatchService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<MatchDetail>> getMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    @GetMapping("{matchId}")
    public ResponseEntity<MatchDetail> getMatch(@PathVariable("matchId") int matchId) {
        MatchDetail match;

        try {
            match = matchService.getMatch(matchId);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(match);
    }

    @PostMapping
    public ResponseEntity<MatchDetail> createMatch(@RequestBody UpdateMatch match) {
        MatchDetail matchDetail;

        try {
            matchDetail = matchService.addMatch(match);
        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).body(null);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.created(URI.create("/matches/" + matchDetail.getId())).build();
    }

    @PutMapping("{matchId}")
    public ResponseEntity<MatchDetail> updateMatch(
            @PathVariable("matchId") int matchId,
            @RequestBody UpdateMatch match) {
        MatchDetail matchDetail;

        try {
            matchDetail = matchService.updateMatch(matchId, match);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(matchDetail);
    }

    @DeleteMapping("{matchId}")
    public ResponseEntity<MatchDetail> deleteMatch(@PathVariable("matchId") int matchId) {

        try {
            matchService.deleteMatch(matchId);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
