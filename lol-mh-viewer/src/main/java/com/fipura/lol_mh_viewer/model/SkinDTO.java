package com.fipura.lol_mh_viewer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "skins")
@NoArgsConstructor
public class SkinDTO {

    @Id
    @SequenceGenerator(name = "skin_sequence", sequenceName = "skin_sequence", allocationSize = 1)
    @GeneratedValue(generator = "skin_sequence")
    private Integer id;
    private String name;

    public SkinDTO(String name) {
        this.name = name;
    }

}
