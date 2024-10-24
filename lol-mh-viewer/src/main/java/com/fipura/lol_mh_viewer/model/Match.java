package com.fipura.lol_mh_viewer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Match {

    @Id
    @SequenceGenerator(name = "match_sequence", sequenceName = "match_sequence", allocationSize = 1)
    @GeneratedValue(generator = "match_sequence")
    private Integer id;
    @Enumerated(EnumType.STRING)
    private GameOutcome gameOutcome;
    @Enumerated(EnumType.STRING)
    private ChampionEnum champion;
    @NotNull
    @Min(0)
    private int kills;
    @NotNull
    @Min(0)
    private int deaths;
    @NotNull
    @Min(0)
    private int assists;
    @NotNull
    @Min(180)
    private int gameDurationSeconds;


    public Match(GameOutcome gameOutcome, ChampionEnum champion, int kills, int deaths, int assists, int gameDurationSeconds) {
        this.gameOutcome = gameOutcome;
        this.champion = champion;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.gameDurationSeconds = gameDurationSeconds;
    }

    public String getFormattedDuration() {
        int minutes = gameDurationSeconds / 60;
        int seconds = gameDurationSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

}
