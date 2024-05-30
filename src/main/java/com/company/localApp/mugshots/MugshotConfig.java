package com.company.localApp.mugshots;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class MugshotConfig {
    @Bean
    CommandLineRunner commandLineRunner(MugshotRepo mugshotRepo) {
        return args -> {
            Mugshot mariam = new Mugshot(
                    1L, "mariam", "describe her"
            );
            Mugshot alex = new Mugshot(
                  2L, "alex", "describe him"
            );
            mugshotRepo.saveAll(
                    List.of(mariam, alex)
            );
        };
    }
}