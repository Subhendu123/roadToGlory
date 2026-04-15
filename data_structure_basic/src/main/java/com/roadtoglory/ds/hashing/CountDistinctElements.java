/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.hashing;
/*
*
*

This class CountDistinctElements is created and managed by subhe
Created on 26-03-2026 at 07:40 for the project UDemy + LC

*
*
*/

import java.util.HashMap;
import java.util.Map;

public class CountDistinctElements {
    public int count(int[] inputArr) {
        Map<Integer, Integer> distCalcMap = new HashMap<>();
        for (int input : inputArr) {
            distCalcMap.put(input, distCalcMap.getOrDefault(input, 0) + 1);
        }
        return distCalcMap.size();
    }

    public Map frequencies(int[] inputArr) {
        Map<Integer, Integer> distCalcMap = new HashMap<>();
        for (int input : inputArr) {
            distCalcMap.put(input, distCalcMap.getOrDefault(input, 0) + 1);
        }
        return distCalcMap;
    }
}
