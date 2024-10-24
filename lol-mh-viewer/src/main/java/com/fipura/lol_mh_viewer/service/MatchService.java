package com.fipura.lol_mh_viewer.service;

import com.fipura.lol_mh_viewer.model.Match;
import com.fipura.lol_mh_viewer.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository repository;


    @Autowired
    public MatchService(MatchRepository repository) {
        this.repository = repository;
    }

    public List<Match> getMatches() {
        return repository.findAll();
    }

    public void addMatch(Match match) {
        repository.save(match);
    }

    public void deleteMatch(Integer id) {
        Optional<Match> matchOptional = repository.findById(id);

        if (matchOptional.isEmpty()) {
            throw new IllegalStateException("There's no such match with the id you have provided");
        }
        repository.deleteById(id);
    }

    @Transactional
    public Match updateMatch(Integer id, Match match) {
        return repository.findById(id).map(existingMatch -> {
            existingMatch.setGameOutcome(match.getGameOutcome());
            existingMatch.setChampion(match.getChampion());
            existingMatch.setKills(match.getKills());
            existingMatch.setAssists(match.getAssists());
            existingMatch.setDeaths(match.getDeaths());
            existingMatch.setGameDurationSeconds(match.getGameDurationSeconds());
            return repository.save(existingMatch);
        }).orElseThrow(() -> new IllegalStateException("There's no such match with the id you have provided, therefore we can't update."));
    }

}
