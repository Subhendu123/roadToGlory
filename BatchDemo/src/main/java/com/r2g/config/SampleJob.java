package com.r2g.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


/*
*
*
   This is created by Subhendu (2025) for the project: BatchDemo
        
   @Package name com.r2g.config
   @Author Subhendu
   @Date 23-Jan-2025 08:03
*
*
*/
@Configuration
public class SampleJob
{

    private Logger LOG = LogManager.getLogger(SampleJob.class);

    @Bean
    public Job executeJob(JobRepository jobRepository, Step step) {
        LOG.info("invoking read numbers job...");
        return new JobBuilder("readNumbers", jobRepository)
//                       .listener(listener)
                       .start(step)
                       .build();
    }

    @Bean
    public Step executeStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      FirstItemReader firstItemReader, CustomIntegerProcessor processor, CustomItemWriter writer) {
        LOG.info("invoking executeStep() ...");
        return new StepBuilder("step11", jobRepository)
                       .<Integer, Long>chunk(3, transactionManager)
                       .reader(firstItemReader)
                       .processor(processor)
                       .writer(writer)
                       .build();
    }


}
