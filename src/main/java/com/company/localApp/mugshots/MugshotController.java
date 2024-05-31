package com.company.localApp.mugshots;

import jakarta.validation.Valid;
import org.hibernate.engine.internal.ImmutableEntityEntry;
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
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneMugshot(@PathVariable ("id") Long id){
        try {
            Mugshot mugshot = mugshots.getOneMugshot(id);
            return ResponseEntity.ok(mugshot);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Mugshot with id " + id + " does not exist");
        }
    }

    @PostMapping
    public ResponseEntity<String> addMugshot(@RequestBody Mugshot mugshot){ //we take request body and map it into student
    String message=mugshots.addMugshot(mugshot);
    return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMugshot(@PathVariable("id") Long id) {
        try {
            String message = mugshots.deleteMugshot(id);
            return ResponseEntity.ok(message);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> editMugshot(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description) {
        try {
            String message = mugshots.editMugshot(id, name, description);
            return ResponseEntity.ok(message);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
