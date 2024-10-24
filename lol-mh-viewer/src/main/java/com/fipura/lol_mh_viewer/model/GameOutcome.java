package com.fipura.lol_mh_viewer.model;

public enum GameOutcome {
    VICTORY("Victory"), DEFEAT("Defeat"), REMAKE("Remake");

    public final String label;

    GameOutcome(String label) {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }

}
