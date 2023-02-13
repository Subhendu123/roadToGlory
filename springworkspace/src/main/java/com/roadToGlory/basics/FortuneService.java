package com.roadToGlory.basics;

import org.springframework.stereotype.Component;

@Component
public class FortuneService {

    private String fortune = "Today is a Lucky Day!";

    public String getFortune(){
        return fortune;
    }

    public void setFortune(String fortune){
        this.fortune = fortune ;
    }
}
