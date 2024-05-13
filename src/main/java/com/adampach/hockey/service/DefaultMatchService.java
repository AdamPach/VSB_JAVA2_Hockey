package com.adampach.hockey.service;

import com.adampach.hockey.dto.MatchDetail;
import com.adampach.hockey.dto.UpdateMatch;
import com.adampach.hockey.exception.EntityNotFoundException;
import com.adampach.hockey.mapper.MatchDetailMapper;
import com.adampach.hockey.mapper.UpdateMatchMapper;
import com.adampach.hockey.model.Match;
import com.adampach.hockey.repository.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultMatchService implements MatchService {

    private final MatchRepository matchRepository;
    private final TeamService teamService;
    private final Logger logger = LoggerFactory.getLogger(DefaultMatchService.class);

    public DefaultMatchService(MatchRepository matchRepository, TeamService teamService) {
        this.matchRepository = matchRepository;
        this.teamService = teamService;
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

    @Override
    public MatchDetail addMatch(UpdateMatch match) {
        Match newMatch;
        UpdateMatchMapper mapper = new UpdateMatchMapper();

        logger.warn(match.toString());

        try{
            Match mappedMatch = mapper.map(match);

            mappedMatch.setHomeTeam(teamService.getTeamById(match.getHomeTeamId()));
            mappedMatch.setAwayTeam(teamService.getTeamById(match.getAwayTeamId()));

            newMatch = matchRepository.save(mappedMatch);
        }catch (DataIntegrityViolationException e)
        {
            logger.warn("Trying to save a existing match");
            throw e;
        }

        return new MatchDetailMapper().map(newMatch);
    }

    @Override
    public MatchDetail updateMatch(int matchId, UpdateMatch match) {
        Match oldMatch = getMatchById(matchId);

        oldMatch.setHomeTeam(teamService.getTeamById(match.getHomeTeamId()));
        oldMatch.setAwayTeam(teamService.getTeamById(match.getAwayTeamId()));

        oldMatch.setHomeTeamScore(match.getHomeScore());
        oldMatch.setAwayTeamScore(match.getAwayScore());

        oldMatch.setMatchDate(match.getMatchDate());
        oldMatch.setDescription(match.getDescription());

        matchRepository.save(oldMatch);

        return new MatchDetailMapper().map(oldMatch);
    }

    @Override
    public void deleteMatch(int matchId) {
        Match match = getMatchById(matchId);

        matchRepository.delete(match);
    }

    private Match getMatchById(int id) {
        Optional<Match> match = matchRepository.findById(id);

        if (match.isEmpty()) {
            throw new EntityNotFoundException("No player found with id " + id);
        }

        return match.get();
    }
}
