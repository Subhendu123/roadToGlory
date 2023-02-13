package com.roadToGlory.configs;

import com.roadToGlory.basics.CricketCoach;
import com.roadToGlory.basics.FortuneService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.roadToGlory" ,lazyInit = true)
public class SportsConfig {

    private static final Logger LOG = Logger.getLogger(SportsConfig.class);

    @Bean
    public FortuneService fortuneService(){
        LOG.info("Fortune Service Bean is getting Created...");
        return new FortuneService();
    }
    @Bean
    public CricketCoach cricketCoach(){
        LOG.info("Cricket Coach bean is getting Created...");
        return new CricketCoach(fortuneService());
    }
}
