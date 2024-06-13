package com.company.localApp.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner2(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            // Create roles if they don't exist
            Optional<Role> userRoleOpt = Optional.ofNullable(roleRepo.findByName("ROLE_USER"));
            Optional<Role> adminRoleOpt = Optional.ofNullable(roleRepo.findByName("ROLE_ADMIN"));

            Role userRole = userRoleOpt.orElseGet(() -> {
                Role newUserRole = new Role();
                newUserRole.setName("ROLE_USER");
                return roleRepo.save(newUserRole);
            });

            Role adminRole = adminRoleOpt.orElseGet(() -> {
                Role newAdminRole = new Role();
                newAdminRole.setName("ROLE_ADMIN");
                return roleRepo.save(newAdminRole);
            });

            // Create users
            User mariam = new User(
                    1L,
                    "mariam",
                    passwordEncoder.encode("Mariam123"),
                    "emma21@gmail.com",
                    "this is Mariam",
                    null,
                    Arrays.asList(userRole)
            );

            User alex = new User(
                    2L,
                    "alex",
                    passwordEncoder.encode("AlexRogers123"),
                    "emm@gmail.com",
                    "this is descr",
                    null,
                    Arrays.asList(userRole)
            );

            User admin = new User(
                    3L,
                    "admin",
                    passwordEncoder.encode("admin"),
                    "admin@example.com",
                    "Admin user",
                    null,
                    Arrays.asList(adminRole)
            );

            userRepo.saveAll(List.of(mariam, alex, admin));
        };
    }
}
