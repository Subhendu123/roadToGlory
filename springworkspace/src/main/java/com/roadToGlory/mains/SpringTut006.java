package com.roadToGlory.mains;

import com.roadToGlory.annotations.BasketballCoach;
import com.roadToGlory.basics.Coach;
import com.roadToGlory.basics.CricketCoach;
import com.roadToGlory.configs.SportsConfig;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Clock;


public class SpringTut006 {

    private static final Logger log = Logger.getLogger(SpringTut006.class);


    public static void main(String[] args) {
        Long start = Clock.systemDefaultZone().millis();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportsConfig.class);
        Coach coach = context.getBean("basketballCoach", BasketballCoach.class);
        log.info(coach.getMyInstruction());
        Coach c = context.getBean("cricketCoach", CricketCoach.class);
        log.info("Cricket coach: "+c.getMyInstruction());
        Long end = Clock.systemDefaultZone().millis();
        log.info("The time elapsed : "+(end - start) + " ms");

    }
}
