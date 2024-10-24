package com.fipura.lol_mh_viewer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fipura.lol_mh_viewer.model.Champion;
import com.fipura.lol_mh_viewer.model.ChampionDTO;
import com.fipura.lol_mh_viewer.model.SkinDTO;
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

    public ChampionDTO getChampionData(String championName) {
        try {
            //get the index 0 of the versions array
            ResponseEntity<String[]> versionResponse = restTemplate.getForEntity(VERSION_URL, String[].class);
            String version = versionResponse.getBody()[0];
            //replace {version} with vesion and {champion} with championName

            String url = BASE_URL.replace("{version}", version).replace("{champion}", championName);

            ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
            JsonNode skinsNode = response.getBody().get("data").get(championName).get("skins");
            List<SkinDTO> skins = new ArrayList<>();
            for (JsonNode skinNode : skinsNode) {
                skins.add(new SkinDTO(skinNode.get("name").asText()));
            }

            ChampionDTO championToAdd = new ChampionDTO(championName, skins);
            return repository.save(championToAdd);

        } catch (Exception e) {
            throw new RuntimeException("Error fetching data for champion: " + championName, e);
        }
    }

    public List<ChampionDTO> getAllChampionsData(Champion[] champions) {
        return Arrays.stream(champions).map(champion -> getChampionData(champion.getName())).collect(Collectors.toList());
    }


}
