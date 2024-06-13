package com.company.localApp.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepo.findByName("ROLE_USER"));
        user.setRoles(roles);
        userRepo.save(user);
    }

    public boolean existsByNickname(String nickname) {
        return userRepo.existsByNickname(nickname);
    }

    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Transactional
    public void updateRole(Long userId, String roleName) {
        logger.debug("Updating role for user ID: {}", userId);
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
        Role role = roleRepo.findByName(roleName);
        if (role == null) {
            logger.debug("Role {} not found, creating a new one", roleName);
            role = new Role();
            role.setName(roleName);
            roleRepo.save(role);
        }
        user.getRoles().clear();
        user.getRoles().add(role);
        userRepo.save(user);
        logger.debug("Role updated successfully for user ID: {}", userId);
    }
}
