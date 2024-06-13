package com.company.localApp.users;

import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setNickname(userDTO.getNickname());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setDescription(userDTO.getDescription());
        user.setImagePath(userDTO.getImagePath());
        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setNickname(user.getNickname());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setDescription(user.getDescription());
        userDTO.setImagePath(user.getImagePath());
        return userDTO;
    }
}
