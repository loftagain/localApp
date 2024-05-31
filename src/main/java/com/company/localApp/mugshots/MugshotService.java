package com.company.localApp.mugshots;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
//Service: Should contain the business logic and interact with
// repositories or other services.
@Service
public class MugshotService {
    private final MugshotRepo mugshotRepo;
    @Autowired
    public MugshotService(MugshotRepo mugshotRepo) {
        this.mugshotRepo = mugshotRepo;
    }

    public List<Mugshot> getMugshots(){
        return mugshotRepo.findAll();
    }

    public Mugshot getOneMugshot(Long id){
        return mugshotRepo.findById(id)
          .orElseThrow(() -> new IllegalStateException("Mugshot with id " + id + " does not exist"));
    }
    public String addMugshot(Mugshot mugshot) {
        Mugshot savedMugshot=mugshotRepo.save(mugshot);
        if (savedMugshot != null) {
            return "Mugshot saved successfully!";
        } else {
            return "Failed to save mugshot";
        }
    }
//deleteById will either successfully delete the record or throw an exception if something goes wrong
//Instead of manually checking for existence after deletion, rely on the exception handling mechanism
//provided by Spring Data JPA. If deleteById fails, it will throw an exception, which you can catch and handle.
public String deleteMugshot(Long id) {
    boolean exists = mugshotRepo.existsById(id);
    if (!exists) {
        throw new IllegalStateException("Mugshot with id " + id + " does not exist");
    }
    mugshotRepo.deleteById(id);
    return "Mugshot deleted successfully!";
}

    @Transactional
    public String editMugshot(Long id, String name, String description) {
        Mugshot mugshot = mugshotRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("Mugshot with id " + id + " does not exist"));

        boolean isUpdated = false;

        if (name != null && !name.isBlank() && !Objects.equals(mugshot.getName(), name)) {
            mugshot.setName(name);
            isUpdated = true;
        }

        if (description != null && !description.isBlank() && !Objects.equals(mugshot.getDescription(), description)) {
            mugshot.setDescription(description);
            isUpdated = true;
        }

        if (isUpdated) {
            // The entity is automatically managed and will be updated on transaction commit
            return "Mugshot updated successfully!";
        } else {
            throw new IllegalStateException("No changes were made to the mugshot.");
        }
    }
}