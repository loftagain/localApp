package com.company.localApp;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Primary
@Component
public class MildHoroscopes implements Interface{
    @Override
    public String getHoroscope(){
        return "Mild Horoscope";
    }
}
