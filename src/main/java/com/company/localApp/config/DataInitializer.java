package com.company.localApp.config;

import com.company.localApp.users.Role;
import com.company.localApp.users.RoleRepo;
import com.company.localApp.users.User;
import com.company.localApp.users.UserRepo;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    @Transactional
    public void init() {
        logger.info("Initializing database with roles and admin user...");

        if (roleRepo.count() == 0) {
            logger.info("Creating default roles...");
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepo.save(userRole);

            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepo.save(adminRole);
        }

        if (userRepo.count() == 0) {
            logger.info("Creating default admin user...");
            Role adminRole = roleRepo.findByName("ROLE_ADMIN");
            List<Role> roles = new ArrayList<>();
            roles.add(adminRole);
//String password= passwordEncoder.encode("admin");
            User admin = new User(
                    3L,
                    "password",
                    "admin@example.com",
                    "Admin user",
                    null,
                    "image.jpg",
                    roles
            );

            userRepo.save(admin);
        }
    }
}
