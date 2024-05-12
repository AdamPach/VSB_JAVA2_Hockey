package com.adampach.hockey.service;

import com.adampach.hockey.dto.MatchDetail;
import com.adampach.hockey.exception.EntityNotFoundException;
import com.adampach.hockey.mapper.MatchDetailMapper;
import com.adampach.hockey.model.Match;
import com.adampach.hockey.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultMatchService implements MatchService {

    private final MatchRepository matchRepository;

    public DefaultMatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public List<MatchDetail> getAllMatches() {
        MatchDetailMapper mapper = new MatchDetailMapper();

        return matchRepository.findAll().stream().map(mapper::map).toList();
    }

    @Override
    public MatchDetail getMatch(int matchId) {
        MatchDetailMapper mapper = new MatchDetailMapper();
        return mapper.map(getMatchById(matchId));
    }

    private Match getMatchById(int id) {
        Optional<Match> match = matchRepository.findById(id);

        if (match.isEmpty()) {
            throw new EntityNotFoundException("No player found with id " + id);
        }

        return match.get();
    }
}
