package com.r2g.spring.batch.service;

import com.r2g.spring.batch.jobutils.RequestParams;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(value = "batchjobsvc")
public class BatchJobService
{
	
	@Autowired
	JobLauncher jobLauncher;
	
//	@Qualifier("firstJob")
//	@Autowired
//	Job firstJob;
	
	@Qualifier("executeJob")
	@Autowired
	Job executeJob;

	private static Long count = 0L;


	@Qualifier("executePremJob")
	@Autowired
	Job executePremJob;
	
	@Async
	public void startJob(String jobName) {
		
		try {
			Map<String, JobParameter<RequestParams>> mapParam = new HashMap<>();


			JobParameters jobParameters = new JobParametersBuilder()
												  .addDate("time", new Date())
												  .addLong("counter", ++count)
												  .addString("name", jobName)
												  .toJobParameters();
			JobExecution jobExecution = null;
			if(jobName.equals("First Job")) {
				jobExecution = jobLauncher.run(executeJob, jobParameters);
			} else if(jobName.equals("Second Job")) {
				jobExecution = jobLauncher.run(executeJob, jobParameters);
			}
		 else if(jobName.equals("Premier League")) {
			jobExecution = jobLauncher.run(executePremJob, jobParameters);
		}
			System.out.println("Job Execution ID = " + jobExecution.getId());
		}catch(Exception e) {
			System.out.println("Exception while starting job");
			e.printStackTrace();
		}
	}

}
