package com.company.localApp.mugshots;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//Controller: Should handle HTTP requests, responses, and route
// incoming requests to the appropriate service methods.
@RestController
@RequestMapping(path="/mugshots")
public class MugshotController {
private final MugshotService mugshots;
@Autowired
public MugshotController(MugshotService mugshots){
    this.mugshots=mugshots;
}
    @GetMapping
    public List<Mugshot> getMugshots(){
        return mugshots.getMugshots();
    }
}
