package com.fipura.lol_mh_viewer.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkinDTO {
    private String name;

    public SkinDTO(String name){
        this.name = name;
    }

}
