package com.company.localApp.mugshots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controller: Should handle HTTP requests, responses, and route
// incoming requests to the appropriate service methods.
@Controller
@RequestMapping(path="/mugshots")
public class MugshotController {
private final MugshotService mugshots;
@Autowired
public MugshotController(MugshotService mugshots){
    this.mugshots=mugshots;
}

@GetMapping
public String getMugshots(Model model) {
    List<Mugshot> mugshot = mugshots.getMugshots();
    model.addAttribute("mugshot", mugshot);
    model.addAttribute("something", "Displaying Mugshots");
    model.addAttribute("pageTitle", "mugshots");
    model.addAttribute("styles", "mugshots");
    model.addAttribute("pageContent", "mugshots");
    return "layout";
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
