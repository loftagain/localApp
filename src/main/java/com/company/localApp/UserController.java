package com.company.localApp;

import com.company.localApp.mugshots.MugshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //shows spring to return json instead of
//making it look for a template like @Controller does *.*
@RequestMapping("/yardsale")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {this.userService=userService;}
@GetMapping
    public List<UserDTO> getUsers(){
    return userService.getUsers();
}
}