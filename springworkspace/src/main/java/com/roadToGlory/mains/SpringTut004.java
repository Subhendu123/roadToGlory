package com.roadToGlory.mains;

import com.roadToGlory.annotations.BasketballCoach;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTut004 {

    public static void main(String[] args) {
        // Annotations and Autowiring basics

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("annotation-applContext.xml");
        BasketballCoach coach = context.getBean("basketballCoach", BasketballCoach.class);
        System.out.println(coach.getMyInstruction());
        System.out.println("The Fortune teller tells me: "+coach.getFortune());
        System.out.println("The Accessories used here is / are "+coach.getAccessories());
    }
}
