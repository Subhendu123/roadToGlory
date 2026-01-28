/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.leetcodes.twopointers;
/*
*
*

This class ContainsDuplicateII is created and managed by subhe
Created on 26-01-2026 at 15:32 for the project Leetcode #219 - Easy

*
*
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

public class ContainsDuplicateII {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        boolean result = false;
//        if (k >= nums.length) {
//            return false;
//        }
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            if (freqMap.containsKey(nums[i])) {
                // existing or repeating
                int diff = i - freqMap.get(nums[i]);
                if (Math.abs(diff) <= k) {
                    result = true;
                    break;
                }
                else {
                    // Available but the index is not under the constraint k
                    freqMap.put(nums[i], i);
                }

            }
            else {
                freqMap.put(nums[i], i);
            }

        }
        return result;

    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {


        int left = 0;
        int right = left + indexDiff;

        HashSet<Integer> hashSet = new HashSet<>();
        int windowHighest = nums[0];
        int windowLowest =  nums[0];

        for(int i=1;i<=indexDiff;i++){
            // check if this is a satisfactory windows
            int difference = Math.abs(windowHighest - nums[i]);

            if(difference <= valueDiff && i <= indexDiff){
                return true;
            }
            if(nums[i] > windowHighest){
                windowHighest = nums[i];
            }
            if(nums[i] < windowLowest){
                windowLowest = nums[i];
            }

            if(!hashSet.add(nums[i])){
                return true;
            }

        }

        for (int i = 0; i < nums.length; i++) {
treeSet.contains(nums[i])
        }
    }
}
