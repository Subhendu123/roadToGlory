package com.r2g.spring.batch.controller;

/*
*
*
   This is created by Subhendu (2025) for the project: batch-with-rest
        
   @Package name com.r2g.spring.batch.controller
   @Author Subhendu
   @Date 28-Jan-2025 20:55
*
*
*/

import com.r2g.spring.batch.processutils.BatchProcessors;
import com.r2g.spring.batch.service.BatchJobService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BatchController
{


    @Qualifier("batchjobsvc")
    @Autowired
    BatchJobService batchJobService;

    @PostMapping("/v1/process/{id}")
    public String process(@PathVariable int id){
        if(id ==1){
            batchJobService.startJob("First Job");
        }
        else if(id ==2){
            batchJobService.startJob("Second Job");
        }
        else if(id ==3){
            batchJobService.startJob("Premier League");

        }
        else {
            return "Bad Request";
        }
       return "Processing the job...";

    }


}
