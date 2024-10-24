package com.fipura.lol_mh_viewer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "champions")
@NoArgsConstructor
public class Champion {
    @Id
    @SequenceGenerator(name = "champion_sequence", sequenceName = "chapion_sequence", allocationSize = 1)
    @GeneratedValue(generator = "champion_sequence")
    private Integer id;
    @NotNull
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Skin> skins;

    public Champion(String name, List<Skin> skins) {
        this.name = name;
        this.skins = skins;
    }
}
