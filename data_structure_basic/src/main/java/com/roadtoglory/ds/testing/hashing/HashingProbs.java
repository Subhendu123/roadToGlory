/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.hashing;
/*
*
*

This class DoubleHashingJunit is created and managed by subhe
Created on 26-03-2026 at 07:24 for the project udemy hashing

*
*
*/

import com.roadtoglory.ds.hashing.CountDistinctElements;
import com.roadtoglory.ds.hashing.DoubleHashing;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class HashingProbs {

    @Test
    public void doubleHAshing() {
        DoubleHashing doubleHashing = new DoubleHashing();
        doubleHashing.initialize(6);
        doubleHashing.insert(10);
        doubleHashing.insert(7);
        doubleHashing.insert(17);
        doubleHashing.insert(24);
        doubleHashing.insert(31);
        doubleHashing.print();
    }

    @Test
    public void countDistinctEl() {
        CountDistinctElements distinctElements = new CountDistinctElements();
        int[] arr = {15, 12, 13, 12, 12, 13, 13, 18, 15};
        Assert.assertEquals(4, distinctElements.count(arr));
    }

    @Test
    public void freqDistinctEl() {
        CountDistinctElements distinctElements = new CountDistinctElements();
        int[] arr = {15, 12, 13, 12, 12, 13, 13, 18, 15};
        Map<Integer, Integer> disMap = distinctElements.frequencies(arr);
        Assert.assertTrue(disMap.containsKey(15));
        Assert.assertTrue(disMap.containsKey(12));
        Assert.assertTrue(disMap.containsKey(13));
        Assert.assertTrue(disMap.containsKey(18));
        Assert.assertEquals(4, disMap.size());
    }
}
