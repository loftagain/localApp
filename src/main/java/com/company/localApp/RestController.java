package com.company.localApp;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {
    private List<Clientele> theClients;
    @PostConstruct //adds values after the regular constructor has been called
    /* Here we load data (not saved to database):
     */
    public void loadData(){
        theClients=new ArrayList<>();
        theClients.add(new Clientele("LV0110234","+372 29003401","Adam of course"));
        theClients.add(new Clientele("LV0110235","+372 29003402","Rachel Smalls"));
        theClients.add(new Clientele("LV0110236","+372 29003403","Katnipped"));
    }
    /* Mappings begin here:
     */
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to LocalApp";
    }
    @GetMapping("/list") //works
    public List<Clientele> listOfClients(){
    return theClients;
    }
    @GetMapping("/list/{id}")
    public Clientele getClientele(@PathVariable int id){
//if(theClients.get(id)==null){
//    throw new ClientNotFoundException("Client with such id does not exist!");
//}
        if(id>theClients.size()||id<0){
            throw new ClientNotFoundException("Client not found!");
        }
else
    return theClients.get(id);
    }
    private Interface horoscope;
    @Autowired
    public void doSth(Interface horoscope){
        this.horoscope=horoscope;
    }
    @GetMapping("/horoscope")
    public String horoscope(){
        return horoscope.getHoroscope();
    }

}
