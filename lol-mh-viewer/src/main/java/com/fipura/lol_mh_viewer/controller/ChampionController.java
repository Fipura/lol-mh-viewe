package com.fipura.lol_mh_viewer.controller;

import com.fipura.lol_mh_viewer.model.Champion;
import com.fipura.lol_mh_viewer.model.ChampionEnum;
import com.fipura.lol_mh_viewer.model.Skin;
import com.fipura.lol_mh_viewer.repository.ChampionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return Arrays.stream(ChampionEnum.values()).map(ChampionEnum::getName).collect(Collectors.toList());
    }

    @GetMapping("{champion}")
    public List<Skin> getSkins(@PathVariable String champion) {
        Champion championToReturn = championRepository.findByName(champion);
        if (championToReturn == null) {
            throw new IllegalArgumentException("Champion not found");
        }
        return championToReturn.getSkins();
    }
}
