package com.fipura.lol_mh_viewer.controller;

import com.fipura.lol_mh_viewer.model.Match;
import com.fipura.lol_mh_viewer.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService service;

    @Autowired
    public MatchController(MatchService service) {
        this.service = service;
    }

    @GetMapping
    public List<Match> getMatches() {
        return service.getMatches();
    }

    @PostMapping
    public void addMatch(@RequestBody Match match) {
        service.addMatch(match);
    }

    @DeleteMapping("{id}")
    public void deleteMatch(@PathVariable Integer id) {
        service.deleteMatch(id);
    }

    @PutMapping("{id}")
    public void updateMatch(@PathVariable Integer id, @RequestBody Match match) {
        service.updateMatch(id, match);
    }
}
