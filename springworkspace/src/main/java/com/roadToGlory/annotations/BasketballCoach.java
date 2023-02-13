package com.roadToGlory.annotations;

import com.roadToGlory.basics.Coach;
import com.roadToGlory.basics.FortuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BasketballCoach implements Coach {

//    @Autowired
    private FortuneService fortuneService;

    @Autowired
    @Qualifier("footballAccessories")
    private Accessories accessories;



//    @Autowired
//    public BasketballCoach(@Autowired FortuneService fortuneService){
//        System.out.println("BasketBall Coach Constructor!");
//        this.fortuneService = fortuneService;
//    }


    @Autowired
    public void setFortuneService(FortuneService fortuneService){
        this.fortuneService = fortuneService;
        System.out.println("Basketball Coach: In setter method");
    }

    public String getFortune(){
        return this.fortuneService.getFortune();
    }

    public String getAccessories(){
        return this.accessories.getAccessories();
    }

    @Override
    public String getMyInstruction() {
        return "Jump and Run for how many KMs u want!!!";
    }
}
