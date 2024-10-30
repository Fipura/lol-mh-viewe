package com.fipura.lol_mh_viewer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fipura.lol_mh_viewer.model.Champion;
import com.fipura.lol_mh_viewer.model.ChampionEnum;
import com.fipura.lol_mh_viewer.model.Skin;
import com.fipura.lol_mh_viewer.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminChampionService {

    private final RestTemplate restTemplate;
    private final ChampionRepository repository;
    private static final String BASE_URL = "https://ddragon.leagueoflegends.com/cdn/{version}/data/en_US/champion/{champion}.json";
    private static final String VERSION_URL = "https://ddragon.leagueoflegends.com/api/versions.json";

    @Autowired
    public AdminChampionService(RestTemplate restTemplate, ChampionRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    public Champion getChampionData(String championName) {
        try {
            ResponseEntity<String[]> versionResponse = restTemplate.getForEntity(VERSION_URL, String[].class);
            String version = versionResponse.getBody()[0];
            String url = BASE_URL.replace("{version}", version).replace("{champion}", championName);

            ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
            JsonNode skinsNode = response.getBody().get("data").get(championName).get("skins");
            List<Skin> skins = new ArrayList<>();
            for (JsonNode skinNode : skinsNode) {
                skins.add(new Skin(skinNode.get("name").asText()));
            }

            Champion championToAdd = new Champion(championName, skins);

            if (repository.findByName(championName) != null){
                return null;
            }

            return repository.save(championToAdd);

        } catch (Exception e) {
            throw new RuntimeException("Error fetching data for champion: " + championName, e);
        }
    }

    public List<Champion> getAllChampionsData(ChampionEnum[] champions) {
        return Arrays.stream(champions).map(champion -> getChampionData(champion.getName())).collect(Collectors.toList());
    }


}
