package com.fipura.lol_mh_viewer.repository;

import com.fipura.lol_mh_viewer.model.ChampionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionRepository extends JpaRepository<ChampionDTO, Integer> {
    ChampionDTO findByName(String name);
}
