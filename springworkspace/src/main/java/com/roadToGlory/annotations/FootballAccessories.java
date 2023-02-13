package com.roadToGlory.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FootballAccessories implements Accessories{

    private String accessories;
    @Override
    public String getAccessories() {
        return this.accessories;
    }

    @Override
    @Autowired
    public void setAccessories(@Value("${football.accessoriesname}") String accessories) {
        this.accessories = accessories;

    }
}
