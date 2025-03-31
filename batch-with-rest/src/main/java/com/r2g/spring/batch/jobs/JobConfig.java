package com.r2g.spring.batch.jobs;

import com.r2g.spring.batch.models.LeagueTable;
import com.r2g.spring.batch.processutils.CustomIntegerProcessor;
import com.r2g.spring.batch.processutils.CustomItemWriter;
import com.r2g.spring.batch.processutils.FirstItemReader;
import com.r2g.spring.batch.processutils.PremierLeagueItemReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;


/*
*
*
   This is created by Subhendu (2025) for the project: batch-with-rest
        
   @Package name com.r2g.spring.batch.jobs
   @Author Subhendu
   @Date 28-Jan-2025 21:16
*
*
*/

@Component
public class JobConfig
{

   private static final Logger LOG = LogManager.getLogger(JobConfig.class);

    @Bean
    public Job executeJob(JobRepository jobRepository,@Qualifier("normalStep") Step step) {
        LOG.info("invoking read numbers job...");
        return new JobBuilder("readNumbers", jobRepository)
                       //                       .listener(listener)
                       .start(step)
                       .build();
    }

    @Bean
    public Job executePremJob(JobRepository jobRepository,@Qualifier("premStep")  Step step) {
        LOG.info("invoking Premier League Table job...");
        return new JobBuilder("premLeague", jobRepository)
                       //                       .listener(listener)
                       .start(step)
                       .build();
    }


    @Bean
    @Qualifier("normalStep")
    public Step executeStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                                FirstItemReader firstItemReader, CustomIntegerProcessor processor, CustomItemWriter writer) {
        LOG.info("invoking executeStep() ...");
        return new StepBuilder("step11", jobRepository)
                       .<Integer, Long>chunk(3, transactionManager)
                       .reader(firstItemReader)
                       //                       .processor(processor)
                       .writer(writer)
                       .build();
    }

    @Bean
    @Qualifier("premStep")
    public Step executePremStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                            PremierLeagueItemReader firstItemReader) {
        LOG.info("invoking executeStep() ...");
        return new StepBuilder("step11", jobRepository)
                       .<LeagueTable, LeagueTable>chunk(3, transactionManager)
                       .reader(firstItemReader)
//                       .processor(processor)
                       .writer(getWriter())
                       .build();
    }

    @StepScope
    @Bean
    private FlatFileItemWriter<LeagueTable> getWriter()
    {
        FlatFileItemWriter<LeagueTable> writer = new FlatFileItemWriter<>();
        LOG.info("Starting to write using the FlatFileItemWriter.");

        FileSystemResource fileSystemResource = new FileSystemResource("output/output.csv");
        LOG.info("File created "+fileSystemResource.getFilename()+ " at path "+fileSystemResource.getPath());
        writer.setResource(fileSystemResource);
        LOG.info("Adding header callback");
        writer.setHeaderCallback(writer1 -> writer1.write("Position, Club Name  ,Points,Played,Won,Drawn,Lost,GF,GA"));
        LOG.info("setLineAggregator() is invoked.");
        writer.setLineAggregator(new DelimitedLineAggregator<LeagueTable>() {
            {
                setFieldExtractor(new BeanWrapperFieldExtractor<LeagueTable>() {
                    {

                        setNames(new String[] {"position", "teamname", "points", "matchesPlayed", "matchesWon",
                                "matchesDrawn",  "matchesLost", "goalsForwarded", "goalsAccumulated"});
                    }
                });
            }
        });
        writer.setFooterCallback(new FlatFileFooterCallback() {
            @Override
            public void writeFooter(Writer writer) throws IOException {
                writer.write("Created by Spring Batch @ " + new Date());
            }
        });
        return writer;
    }


}
