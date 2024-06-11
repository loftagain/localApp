package com.company.localApp.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner2(UserRepo userRepo) {
        return args -> {
            User mariam = new User(1L, "mariam", "describe her", "password", "e@mail.com");
            User alex = new User(
                    2L, "alex", "describe him","password", "e@mail.com"
            );
            userRepo.saveAll(
                    List.of(mariam, alex)
            );
        };
    }
}