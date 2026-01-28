/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.test;
/*
*
*

This class ConcatStringWordsTesting is created and managed by subhe
Created on 26-01-2026 at 09:20 for the project Leetcode testing 

*
*
*/

import com.roadtoglory.practice.twopointers.ContainsDuplicateII;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ContainsDuplicateIITesting {

    private final ContainsDuplicateII solution = new ContainsDuplicateII();

    @Test
    void testCase1() {
        int k = 3;
        int[] nums = {1, 2, 3, 1};

        Boolean result = solution.containsNearbyDuplicate(nums, k);

        assertEquals(true, result);
    }

    @Test
    void testCase2() {
        int k = 1;
        int[] nums = {1, 0, 1, 1};
        Boolean result = solution.containsNearbyDuplicate(nums, k);

        assertEquals(true, result);
    }

    @Test
    void testCase3() {
        int k = 2;
        int[] nums = {1, 2, 3, 1, 2, 3};
        Boolean result = solution.containsNearbyDuplicate(nums, k);

        assertEquals(false, result);
    }

    @Test
    void testCase62() {
        int k = 2;
        int[] nums = {99, 99};
        Boolean result = solution.containsNearbyDuplicate(nums, k);

        assertEquals(true, result);
    }
}
