package com.adampach.hockey.controller;

import com.adampach.hockey.dto.MatchDetail;
import com.adampach.hockey.exception.EntityNotFoundException;
import com.adampach.hockey.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
