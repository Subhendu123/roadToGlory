/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.greedy;
/*
*
*

This class ActivitySelectionJunit is created and managed by subhe
Created on 04-03-2026 at 08:11 for the project Actvity Selection

*
*
*/

import com.roadtoglory.ds.greedy_algo.ActivitySelection;
import com.roadtoglory.ds.greedy_algo.FractionalKnapsack;
import com.roadtoglory.ds.greedy_algo.HuffmanAlgorithm;
import com.roadtoglory.ds.greedy_algo.JobSequencing;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GreedyJunits {

    @Test
    public void testActivitySelectionJunit() {
        ActivitySelection activitySelection = new ActivitySelection();
        ActivitySelection.Activity activity1 = activitySelection.new Activity(1, 3);
        ActivitySelection.Activity activity2 = activitySelection.new Activity(2, 4);
        ActivitySelection.Activity activity3 = activitySelection.new Activity(10, 11);
        ActivitySelection.Activity activity4 = activitySelection.new Activity(3, 8);
        ActivitySelection.Activity activity5 = activitySelection.new Activity(2, 5);
        ActivitySelection.Activity[] activities = new ActivitySelection.Activity[]{activity1, activity2, activity3, activity4, activity5};
        int maxAct = activitySelection.maxActivity(activities);

        Assert.assertEquals(3, maxAct);


    }

    @Test
    public void test_fractional_knapsack() {
        FractionalKnapsack fractionalKnapsack = new FractionalKnapsack();
        FractionalKnapsack.Item item1 = fractionalKnapsack.new Item(50, 600);
        FractionalKnapsack.Item item2 = fractionalKnapsack.new Item(20, 500);
        FractionalKnapsack.Item item3 = fractionalKnapsack.new Item(30, 400);
        fractionalKnapsack.addItem(item1);
        fractionalKnapsack.addItem(item2);
        fractionalKnapsack.addItem(item3);

        int result = fractionalKnapsack.calculateOptimum(70);
        Assert.assertEquals(1140, result);
    }

    @Test
    public void test_fractional_knapsack_002() {
        FractionalKnapsack fractionalKnapsack = new FractionalKnapsack();
        FractionalKnapsack.Item item1 = fractionalKnapsack.new Item(10, 200);
        FractionalKnapsack.Item item2 = fractionalKnapsack.new Item(5, 50);
        FractionalKnapsack.Item item3 = fractionalKnapsack.new Item(20, 100);
        fractionalKnapsack.addItem(item1);
        fractionalKnapsack.addItem(item2);
        fractionalKnapsack.addItem(item3);

        int result = fractionalKnapsack.calculateOptimum(15);
        Assert.assertEquals(250, result);
    }

    @Test
    public void test_job_sequencing_001() {
        JobSequencing jobSequencing = new JobSequencing();
        jobSequencing.addJob(jobSequencing.new Job(2, 50));
        jobSequencing.addJob(jobSequencing.new Job(2, 60));
        jobSequencing.addJob(jobSequencing.new Job(3, 20));
        jobSequencing.addJob(jobSequencing.new Job(3, 30));

        int maxProfit = jobSequencing.maxProfitByDeadline();
        Assert.assertEquals(140, maxProfit);
    }

    @Test
    public void test_job_sequencing_002() {
        JobSequencing jobSequencing = new JobSequencing();
        jobSequencing.addJob(jobSequencing.new Job(2, 100));
        jobSequencing.addJob(jobSequencing.new Job(1, 50));
        jobSequencing.addJob(jobSequencing.new Job(2, 10));
        jobSequencing.addJob(jobSequencing.new Job(1, 20));
        jobSequencing.addJob(jobSequencing.new Job(3, 30));

        int maxProfit = jobSequencing.maxProfitByDeadline();
        Assert.assertEquals(180, maxProfit);
    }

    @Test
    public void hoffman_coding_001() {
        HuffmanAlgorithm huffmanAlgorithm = new HuffmanAlgorithm();
        Integer[] inputValues = new Integer[]{10, 50, 20, 40, 80};
        Character[] characters = new Character[]{'a', 'd', 'b', 'e', 'f'};
        List<String> codes = huffmanAlgorithm.huffmanCoding(inputValues, characters);
        Assert.assertEquals(5, codes.size());
        for (String code : codes) {
            String[] huffmancodeval = code.split("->");
            if (huffmancodeval[0].equalsIgnoreCase("f")) {
                Assert.assertEquals(0, huffmancodeval[1]);
            }
            else if (huffmancodeval[0].equalsIgnoreCase("d")) {
                Assert.assertEquals(10, huffmancodeval[1]);
            }
            else if (huffmancodeval[0].equalsIgnoreCase("e")) {
                Assert.assertEquals(111, huffmancodeval[1]);
            }
            else if (huffmancodeval[0].equalsIgnoreCase("a")) {
                Assert.assertEquals(1100, huffmancodeval[1]);
            }
            else if (huffmancodeval[0].equalsIgnoreCase("b")) {
                Assert.assertEquals(1101, huffmancodeval[1]);
            }
        }
    }
}
