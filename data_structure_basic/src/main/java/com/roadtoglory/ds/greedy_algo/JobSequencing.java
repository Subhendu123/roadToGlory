/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.greedy_algo;
/*
*
*

This class JobSequencing is created and managed by subhe
Created on 08-03-2026 at 08:22 for the project Udemy Learning

*
*
*/

import java.util.ArrayList;
import java.util.List;

public class JobSequencing {

    private List<Job> customerOrders;

    public void addJob(Job job) {
        if (this.customerOrders == null) {
            this.customerOrders = new ArrayList<>();
        }
        this.customerOrders.add(job);
    }

    public int maxProfitByDeadline() {

//        System.out.println("The jobs before sorting " + this.customerOrders);
        this.customerOrders.sort((j1, j2) -> j1.profit > j2.profit ? -1 : 1);
//        System.out.println("The jobs after sorting " + this.customerOrders);

        int maxProfit = 0;
        // sorting the list with the increasing order of the deadline to get the max
        int maxDeadline = this.customerOrders.stream().max(
                (j1, j2) -> j1.deadline > j2.deadline ? 1 : -1).get().deadline;
//        System.out.println(" MAX DEADLINE: " + maxDeadline);
        int timeIndex = 1;
       /* for (int i = 0; i <= maxDeadline; i++) {
            if (timeIndex <= maxDeadline && timeIndex <= job.deadline) {
                timeIndex++;
                System.out.println("Job Profit: " + job.profit);
                maxProfit += job.profit;
            }

        }*/
        return maxProfit;

    }

    public class Job {
        int deadline;
        int profit;

        public Job(int deadline, int profit) {
            this.deadline = deadline;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "Job [deadline=" + deadline + ", profit=" + profit + "]";
        }
    }
}
