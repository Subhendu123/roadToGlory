package com.roadToGlory.basics;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
// for SpringTut005 - the annotations
//@Component("theCoach")
//@Scope("singleton")
public class CricketCoach implements Coach {

    private FortuneService fortuneService;

    static {
        System.out.println("Inside the static block of Cricket Coach Class");
    }
    {
        System.out.println("Inside local block");
    }

//    @Autowired
    public CricketCoach(FortuneService fortuneService){
        System.out.println("Inside CricketCoach Constructor");
        this.fortuneService = fortuneService;
    }

    public String getFortune(){
        return this.fortuneService.getFortune();
    }

    @Override
    public String getMyInstruction() {
        return "Batting Practice for one hour!";
    }

    // beans scope - on init method
    public void doInitialization(){
        System.out.println("Inside doInitialization of Cricket Coach");
    }

    // on destroy
    public void onDestroy(){
        System.out.println("Inside OnDestroy of Cricket Coach");
        System.out.println("Interestingly I am only called in case of singleton class! :(");

        // NOTE: xmlDestroy() method is called with singleton scope bean but not with prototype because
        //
        //Spring does not manage the complete lifecycle of a prototype bean:
        // the container instantiates, configures, decorates and otherwise assembles a prototype object,
        // hands it to the client and then has no further knowledge of that prototype instance.
        // For releasing resources try to implement a custom bean post processor.
    }

}
