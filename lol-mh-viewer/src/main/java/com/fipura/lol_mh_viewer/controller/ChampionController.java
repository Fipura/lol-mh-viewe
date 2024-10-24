package com.fipura.lol_mh_viewer.controller;

import com.fipura.lol_mh_viewer.model.Champion;
import com.fipura.lol_mh_viewer.model.ChampionDTO;
import com.fipura.lol_mh_viewer.model.SkinDTO;
import com.fipura.lol_mh_viewer.repository.ChampionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/champions")
public class ChampionController {

    private final ChampionRepository championRepository;

    public ChampionController(ChampionRepository championRepository) {
        this.championRepository = championRepository;
    }

    @GetMapping
    public List<String> getChampions() {
        // Return a list of champion names
        return Arrays.stream(Champion.values())
                .map(Champion::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("{champion}")
    public List<SkinDTO> getSkins(@PathVariable String champion){
        ChampionDTO championToReturn = championRepository.findByName(champion);
        if (championToReturn == null){
            throw new IllegalArgumentException("Champion not found");
        }
        return championToReturn.getSkins();
    }
}
