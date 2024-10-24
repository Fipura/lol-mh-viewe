package com.fipura.lol_mh_viewer;

import com.fipura.lol_mh_viewer.model.ChampionEnum;
import com.fipura.lol_mh_viewer.model.GameOutcome;
import com.fipura.lol_mh_viewer.model.Match;
import com.fipura.lol_mh_viewer.repository.MatchRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(MatchRepository repository) {
        return args -> {
            Match match = new Match(
                    GameOutcome.VICTORY,
                    ChampionEnum.VAYNE,
                    10,
                    0,
                    5,
                    1836
            );
            repository.save(match);
        };
    }
}
