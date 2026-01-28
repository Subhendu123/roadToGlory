/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.leetcodes.twopointers;
/*
*
*

This class AvgSubArrayK is created and managed by Subhendu
Created on 05-01-2026 at 21:30 for the project
* Sliding Window Problem of Leetcode - EASY

*
*
*/

public class AvgSubArrayK {


    public static void main(String[] args) {
        int[] numsInp = {1, 12, -5, -6, 50, 3};

        System.out.println(new AvgSubArrayK().findMaxAverage(numsInp, 6514));
    }

    public double findMaxAverage(int[] nums, int k) {
        double maxAvg = -1;
        if (nums == null || nums.length <= k) {
            for (int i = 0; i < nums.length; i++)
                maxAvg = nums[i] + maxAvg;
            return maxAvg / k;
        }
        for (int i = 0; i < nums.length; i++) {
            double sum = 0;
            // iterate over the next elements
            // if the K is still less than the total no of elements
            if (i + k <= nums.length) {
                for (int j = i; j < i + k; j++)
                    sum = sum + nums[j];
                double avg = sum / k;

                if (maxAvg == 0) {
                    maxAvg = avg;
                }
                if (avg > maxAvg) {
                    maxAvg = avg;
                }
            }

        }
        return maxAvg;
    }

    public double findMaxAverageWithO_N(int[] nums, int k) {
        double maxSum = 0;
        if (nums != null && nums.length <= k) {
            for (int i = 0; i < nums.length; i++)
                maxSum = nums[i] + maxSum;
            return maxSum / k;
        }
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum = sum + nums[i];
        }
        maxSum = sum;
        for (int i = 0; (i + k) < nums.length; i++) {

            sum = sum - nums[i];
            sum = sum + nums[i + k];

            if (sum > maxSum) {
                maxSum = sum;
            }

        }
        return maxSum / k;
    }
}
