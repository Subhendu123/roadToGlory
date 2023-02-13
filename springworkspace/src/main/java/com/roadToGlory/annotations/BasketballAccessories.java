package com.roadToGlory.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BasketballAccessories implements Accessories{

    private String accessories;

    @Override
    public String getAccessories() {
        return accessories;
    }

    @Override
    @Autowired
    public void setAccessories(@Value("Basketball") String accessories) {
        this.accessories = accessories;
    }

//    @Override
//    public void setAccessories(String accessories) {
//        this.accessories = accessories;
//    }
}
