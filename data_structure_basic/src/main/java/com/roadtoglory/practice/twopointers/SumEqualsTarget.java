/*
 * Copyright (c) 2025.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.practice.twopointers;
/*
*
*

This class SumEqualsTarget is created and managed by subhe
Created on 22-12-2025 at 23:46 for the project Chat GPT Problem
*
* Example: Two Sum (Sorted Array)

Problem
Find two numbers whose sum equals target.

*
*
*/

public class SumEqualsTarget {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int targetVal = 12;
        int[] result = findNumbersWithSumEqualsTarget(array, targetVal);
    }

    private static int[] findNumbersWithSumEqualsTarget(int[] array, int targetVal) {
        int[] result = new int[array.length * 2];
        int resultIndx = 0;
        int j = array.length - 1;
        int i = 0;
        while (i < array.length) {
            int sum = array[i] + array[j];
            if (sum == targetVal) {
                result[resultIndx++] = array[i];
                result[resultIndx++] = array[j];
                System.out.println("Found the details. Tha val are " + array[i] + " and " + array[j]);
                i++;
                j--;
                // print and return or store it in some other var
            }
            else if (sum < targetVal) {
                // need to increase the i value.
                i++;
            }
            else {
                // sum > target. So, right  pointer to be shifted.
                j--;
            }
        }
        return result;
    }

}