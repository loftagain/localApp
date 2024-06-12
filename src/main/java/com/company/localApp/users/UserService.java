package com.company.localApp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserDTOMapper userDTOMapper;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    @Autowired
    public UserService(UserDTOMapper userDTOMapper, UserRepo userRepo, RoleRepo roleRepo) {
        this.userDTOMapper = userDTOMapper;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }


    public List<UserDTO> getUsers(){
        return userRepo.findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    public void save(User user, String role) {
        Role assignedRole;
        if ("admin".equalsIgnoreCase(role)) {
            assignedRole = roleRepo.findByName("ROLE_ADMIN");
        } else {
            assignedRole = roleRepo.findByName("ROLE_USER");
        }
        user.setRoles(new HashSet<>(Arrays.asList(assignedRole)));
        userRepo.save(user);
    }
}
