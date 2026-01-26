/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.practice.twopointers;
/*
*
*

This class LongestHarmomiousSeq is created and managed by subhe
Created on 08-01-2026 at 21:12 for the project Leetcode

*
*
*/

import java.util.Arrays;
import java.util.TreeMap;

public class LongestHarmomiousSeq {

    public static void main(String[] args) {
        int[] nums = {-1, 0, -1, 0, -1, 0, -1};
//        int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};
        System.out.println("Total is " + new LongestHarmomiousSeq().findLHS(nums));
        System.out.println("2. Total is " + new LongestHarmomiousSeq().longestHarmSqHash(nums));


    }

    public int longestHarmSqHash(int[] nums) {
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        int maxLength = 0;
        int prevKey = 0;
        int i = 0;
        for (int key : freq.keySet()) {
            if (i == 0) {
                prevKey = key;
            }
            else {
                int diff = key - prevKey;

                if (diff == 1) {
                    int adjKeyCount = freq.get(key) + freq.get(prevKey);
                    maxLength = Math.max(maxLength, adjKeyCount);
                }
                prevKey = key;

            }
            i++;
        }
        return maxLength;
    }

    public int longestHarmSeq(int[] a) {

        Arrays.sort(a);
        int rightP = 1;
        int leftP = 0;
        int res = 0;
        while (rightP < a.length) {
            int diff = a[rightP] - a[leftP];
            if (diff == 1) {
                res = Math.max(res, rightP - leftP + 1);
                rightP++;
            }
            else if (diff < 1) {
                rightP++;
            }
            else {
                leftP++;
            }
        }
        return res;
    }


    public int findLHS(int[] nums) {

        int min = nums[0];
        int max = nums[0];
        int[] out = new int[nums.length];
        int index = -1;
        int longestLen = 0;
        int longestLenS = 0;
        for (int i = 0; i < nums.length; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }

            int difference = nums[i] - min;
            if (Math.abs(difference) <= 2) {
                out[++index] = nums[i];
                if (max < nums[i]) {
                    max = nums[i];
                }
            }
        }
        if (min == max) {
            return 0;
        }
        System.out.println("min " + min + " , max: " + max);
        for (int i = 0; i <= index; i++) {

//            if (out[i] == 0) {
//                continue;
//            }

            if (Math.abs(max - out[i]) < 2) {
//                System.out.println(out[i] + " : max ");
                longestLen++;
            }
            if (Math.abs(out[i] - min) < 2) {
//                System.out.println(out[i] + " : min ");
                longestLenS++;
            }
        }
        int longestVal = longestLen > longestLenS ? longestLen : longestLenS;
        return longestVal > 1 ? longestVal : 0;
    }
}
