package com.company.localApp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserDTOMapper userDTOMapper;
    private final UserRepo userRepo;
    @Autowired
    public UserService(UserDTOMapper userDTOMapper, UserRepo userRepo) {
        this.userDTOMapper = userDTOMapper;
        this.userRepo = userRepo;
    }


    public List<UserDTO> getUsers(){
        return userRepo.findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }
}
