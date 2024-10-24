package com.fipura.lol_mh_viewer.repository;

import com.fipura.lol_mh_viewer.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Integer> {
}
