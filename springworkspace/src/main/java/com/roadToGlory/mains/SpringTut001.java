package com.roadToGlory.mains;
import com.roadToGlory.basics.Coach;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class SpringTut001 {

    private static final Logger log = Logger.getLogger(SpringTut001.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        log.warn("Test Warn!");
//      Coach coach = ctx.getBean("theCoach", Coach.class);
        Coach coach = ctx.getBean("secondCoach", Coach.class);
        log.info(coach.getMyInstruction());

    }
}
