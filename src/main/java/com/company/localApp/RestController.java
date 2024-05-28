package com.company.localApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
@org.springframework.web.bind.annotation.RestController

public class RestController {
    private Interface horoscope;
    @Autowired
    public void doSth(Interface horoscope){
        this.horoscope=horoscope;
    }
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to LocalApp";
    }
    @GetMapping("/horoscope")
    public String horoscope(){
        return horoscope.getHoroscope();
    }
}
