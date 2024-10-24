package com.fipura.lol_mh_viewer.controller;

import com.fipura.lol_mh_viewer.model.Champion;
import com.fipura.lol_mh_viewer.model.ChampionDTO;
import com.fipura.lol_mh_viewer.service.AdminChampionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/champions")
public class AdminChampionController {

    private final AdminChampionService adminChampionService;

    public AdminChampionController(AdminChampionService adminChampionService) {
        this.adminChampionService = adminChampionService;
    }

    @GetMapping
    public ResponseEntity<List<ChampionDTO>> getAllChampions() {
        List<ChampionDTO> champions = adminChampionService.getAllChampionsData(Champion.values());
        return ResponseEntity.ok(champions);
    }
}
