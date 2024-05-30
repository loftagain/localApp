package com.company.localApp.mugshots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

}