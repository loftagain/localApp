package com.company.localApp.mugshots;

import com.company.localApp.Person;
import jakarta.validation.Valid;
import org.hibernate.engine.internal.ImmutableEntityEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    String getPeople(Model model){
    model.addAttribute("something", "this is coming from a controller"); //just an attribute
    model.addAttribute("mugshot",
            mugshots.getMugshots()
    );
        return "mugshots"; //html name
    }
//    @GetMapping
//    String getPeople(Model model){
//    model.addAttribute("something", "this is coming from a controller");
//    model.addAttribute("people", Arrays.asList(
//            new Person(5L,"John", "description")
//    ));
//        return "people";
//    }
//    @GetMapping
//    public List<Mugshot> getMugshots(){
//        return mugshots.getMugshots();
//    }
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
