package com.company.localApp;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class DetailedHoroscopes implements Interface{
    @Override
    public String getHoroscope(){
        return "Detailed Horoscope";
    }
}
