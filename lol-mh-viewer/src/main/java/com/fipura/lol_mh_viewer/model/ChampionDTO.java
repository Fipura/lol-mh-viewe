package com.fipura.lol_mh_viewer.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChampionDTO {
    private String name;
    private List<SkinDTO> skins;

    public ChampionDTO(String name, List<SkinDTO> skins) {
        this.name = name;
        this.skins = skins;
    }
}
