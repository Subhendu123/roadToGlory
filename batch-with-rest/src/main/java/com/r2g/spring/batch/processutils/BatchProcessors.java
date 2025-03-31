package com.r2g.spring.batch.processutils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.r2g.spring.batch.jobs.JobConfig;
import com.r2g.spring.batch.models.LeagueTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;


@Configuration
public class BatchProcessors
{

    private static final Logger LOG = LogManager.getLogger(BatchProcessors.class);



    @Autowired
    private FirstItemReader firstItemReader;

    @Autowired
    private CustomItemWriter customItemWriter;

    @Bean
    public FirstItemReader reader() {

        LOG.info("The File Item reader is getting started....");
        return firstItemReader;
    }

    @Bean
    public CustomIntegerProcessor processor() {

        LOG.info("Inside the processor invokation...");
        return new CustomIntegerProcessor();
    }

    @Bean
    public CustomItemWriter writer(DataSource dataSource) {
        LOG.info("Inside the writer....");
        return customItemWriter;
    }

}
