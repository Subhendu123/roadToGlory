package com.roadToGlory.mains;

import com.roadToGlory.basics.CricketCoach;
import com.roadToGlory.basics.HockeyCoach;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTut002 {

    private static final Logger log = Logger.getLogger(SpringTut002.class);


    public static void main(String[] args) {

        // constructor injection
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        CricketCoach coach = ctx.getBean("thirdCoach", CricketCoach.class);
        log.info(coach.getMyInstruction());
        log.info(coach.getFortune());
        System.out.println(coach.getMyInstruction());
        System.out.println(coach.getFortune());

        // setter injection
        HockeyCoach hockeyCoach = ctx.getBean("lastCoach" , HockeyCoach.class);
        log.warn("Hockey Coach name "+hockeyCoach.getName());
        log.info(hockeyCoach.getAge());
        log.info(hockeyCoach.getFortuneService().getFortune());
        log.info(hockeyCoach.getMyInstruction());

//        SingletonClass sngl = SingletonClass.getInstance();

    }
}
