package com.roadToGlory.mains;

import com.roadToGlory.basics.Coach;
import com.roadToGlory.basics.CricketCoach;
import com.roadToGlory.basics.TrackCoach;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringTut005 {

    private static final Logger log = Logger.getLogger(SpringTut005.class);


    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("annotation-applContext.xml");
        Coach firstCoach = ctx.getBean("theCoach", CricketCoach.class);
        log.info("firstCoach "+firstCoach);

        Coach secondCoach = ctx.getBean("theCoach", CricketCoach.class);
        log.info("secondCoach "+secondCoach);
        boolean isSame = firstCoach == secondCoach;
        log.error("firstCoach and secondCoach are the same one! Is it? "+isSame);

        ctx.close();


    }
}
