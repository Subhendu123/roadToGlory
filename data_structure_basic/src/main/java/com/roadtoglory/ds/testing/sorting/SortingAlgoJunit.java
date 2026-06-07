/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.sorting;
/*
*
*

This class SortingAlgoJunit is created and managed by subhe
Created on 03-06-2026 at 17:54 for the project Sorting Techniques Testing

*
*
*/

import com.roadtoglory.ds.sorting.SortingAlgorithms;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SortingAlgoJunit {

    private static final int MAX_SIZE = 20;

    @Test
    public void bubbleSort() {
        int[] input = {15, 20, 10, 5, 30, 25, 1, 7, 2, 3, 21, 50};
        int[] output = new SortingAlgorithms().bubbleSort(input);
        Assert.assertEquals("Passed", input, output);
        System.out.println("The bubble sorting of the input is : ");
        for (int e : output) {
            System.out.print(e + " ,");
        }
        System.out.println();

    }

    @Test
    public void selectionSort() {
        int[] input = {-15, 20, 10, 5, 30, 25, 1, 7, 2, 3, 21, 50, 0};
        int[] output = new SortingAlgorithms().selectionSort(input);
        Assert.assertEquals("Passed", input, output);
        System.out.println("The Selection sorting of the input is : ");
        for (int e : output) {
            System.out.print(e + " ,");
        }
        System.out.println();
    }

    @Test
    public void insertionSort() {
        int[] input = {-1, 20, 10, 5, 30, 25, 1, 7, 2, 3, 21, 50, 0, 15};
        int[] output = new SortingAlgorithms().insertionSort(input);
        Assert.assertEquals("Passed", input, output);
        System.out.println("The Insertion sorting of the input is : ");
        for (int e : output) {
            System.out.print(e + " ,");
        }
        System.out.println();
    }

    @Test
    public void mergeSort() {
        int[] input = {5, 7, 3, 6, 2, 10, 25, 15, 0};
//        int[] input = {-1, 20, 10, 5, 30, 25, 1, 7, 2, 3, 21, 50, 0, 15};
//        int[] input = {9, 3, 7, 1, 5};
//        int[] input = {9, 8, 7, 6, 5, 4, 3, 2, 1};

        int[] output = new SortingAlgorithms().mergeSort(input);
        Assert.assertEquals("Passed", input.length, output.length);
        System.out.println("The Merge sorting of the input is : ");
        for (int e : output) {
            System.out.print(e + " ,");
        }
        System.out.println();

    }

    @Test
    public void quickSort() {
//        int[] input = {5, 7, 3, 6, 2, 10, 25, 15, 0};
//        int[] input = {-1, 20, 10, 5, 30, 25, 1, 7, 2, 3, 21, 50, 0, 15};
//        int[] input = {9, 3, 7, 1, 5, 5};
//        int[] input = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] input = new int[MAX_SIZE];
        int index = -1;
        Set<Integer> duplicateCheck = new HashSet<>();
        while (index != MAX_SIZE - 1) {
            int inputNumber = (int) (Math.random() * 100) + 1;
            duplicateCheck.add(inputNumber);

//            if (duplicateCheck.add(inputNumber)) {
            input[++index] = inputNumber;
//            }
        }
        System.out.println("*************************** Input *******************************");
        for (int e : input) {
            System.out.print(e + " ,");
        }
        System.out.println("\n*****************************************\n");
        int[] output = new SortingAlgorithms().quickSort(input);
        Assert.assertEquals("Passed", input.length, output.length);


        System.out.println("The Quick sorting of the input is : ");
        for (int e : output) {
            if (duplicateCheck.add(e)) {
                throw new IllegalArgumentException("The output is different");
            }
            System.out.print(e + " ,");
        }
        System.out.println();

    }
}
