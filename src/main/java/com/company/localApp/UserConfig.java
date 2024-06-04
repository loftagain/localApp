package com.company.localApp;

import com.company.localApp.mugshots.Mugshot;
import com.company.localApp.mugshots.MugshotRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner2(UserRepo userRepo) {
        return args -> {
            User mariam = new User(1L, "mariam", "describe her", "password");
            User alex = new User(
                    2L, "alex", "describe him","password"
            );
            userRepo.saveAll(
                    List.of(mariam, alex)
            );
        };
    }
}