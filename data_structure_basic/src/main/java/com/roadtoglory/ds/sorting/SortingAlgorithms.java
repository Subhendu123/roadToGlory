/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.sorting;
/*
*
*

This class SortingAlgorithms is created and managed by subhe
Created on 03-06-2026 at 17:48 for the project Sorting Techniques

*
*
*/

public class SortingAlgorithms {

    private static int[] sortAndMerge(int[] first, int[] second) {
        int[] merged = new int[first.length + second.length];
        int mIndex = -1;
        int firstIndex = 0;
        int secondIndex = 0;

        while (true) {
            if (firstIndex < first.length && secondIndex < second.length) {
                if (first[firstIndex] <= second[secondIndex]) {
                    merged[++mIndex] = first[firstIndex++];
                }
                else {
                    merged[++mIndex] = second[secondIndex++];
                }
            }
            else if (firstIndex < first.length && mIndex < merged.length - 1) {
                for (int i = firstIndex; i < first.length; i++) {
                    merged[++mIndex] = first[i];
                }
            }
            else if (secondIndex < second.length && mIndex < merged.length - 1) {
                for (int i = secondIndex; i < second.length; i++) {
                    merged[++mIndex] = second[i];
                }
            }
            else {
                break;
            }
        }

        return merged;
    }

    private static int[] merge(int[] smaller, int[] larger) {
        int[] merged = new int[smaller.length + larger.length];
        int index = 0;
        int lastIndexLarger = 0;
        for (int i = 0; i < smaller.length; i++) {
            int small = smaller[i];
            boolean isSmallAdded = false;
            for (int j = lastIndexLarger; j < larger.length; j++) {
                if (small > larger[j]) {
                    lastIndexLarger = j;
                    merged[index++] = larger[j];
                    lastIndexLarger++;
                }
                else {
                    if (!isSmallAdded) {
                        isSmallAdded = true;
                        merged[index++] = small;
                        lastIndexLarger = j;
                        if (i != smaller.length - 1) {
                            break;
                        }
                    }

                }
                if (isSmallAdded) {
                    merged[index++] = larger[j];
                    lastIndexLarger = j;
                    lastIndexLarger++;
                }
            }
            if (!isSmallAdded) {
                merged[index++] = small;
            }
        }
        return merged;
    }

    private static int[] prepareResult(int[] leftResult, int leftCounter, int pivot, int[] rightResult,
                                       int rightCounter) {
        int resultLen = (leftCounter > -1 ? leftCounter + 1 : 0) + (rightCounter > -1 ? rightCounter + 1 : 0) + 1;
        int[] result = new int[resultLen];
        int resultIndex = -1;
        if (leftResult != null) {
            for (int i = 0; i <= leftCounter; i++) {
                result[++resultIndex] = leftResult[i];
            }
        }
        result[++resultIndex] = pivot;

        if (rightResult != null) {
            for (int i = 0; i <= rightCounter; i++) {
                result[++resultIndex] = rightResult[i];
            }
        }
        return result;
    }

    public int[] bubbleSort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] > input[j]) {
                    int temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
        }
        return input;
    }

    public int[] insertionSort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < i; j++) {
                if (input[i] < input[j]) {
                    int temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
        }
        return input;
    }

    public int[] selectionSort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            int smallest = input[i];
            int smallestIndex = i;
            for (int j = i + 1; j < input.length; j++) {
                if (smallest > input[j]) {
                    smallest = input[j];
                    smallestIndex = j;
                }
            }
            int temp = input[i];
            input[i] = smallest;
            input[smallestIndex] = temp;
        }
        return input;
    }

    public int[] mergeSort(int[] input) {
        return divideAndMergeSort(input, 0, input.length);
    }

    private int[] divideAndMergeSort(int[] input, int start, int length) {
        if (start == length - 1) {
            int[] result = new int[1];
            result[0] = input[start];
            return result;
        }
        int mid = (start + length) / 2;
        int[] result1 = divideAndMergeSort(input, start, mid);
        int[] result2 = divideAndMergeSort(input, mid, length);
        return conquerAndMerge(result1, result2);
    }

    private int[] conquerAndMerge(int[] result1, int[] result2) {
       /* int[] merged;
        if (result1.length < result2.length) {
            merged = merge(result1, result2);
        }
        else {
            merged = merge(result2, result1);
        }
        return merged;*/
        return sortAndMerge(result1, result2);
    }

    public int[] quickSort(int[] input) {
        return quickSortWithPartition(input, input.length - 1);
    }

    private int[] quickSortWithPartition(int[] input, int maxIndex) {
        if (maxIndex < 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        int pivot = input[maxIndex];
        if (maxIndex == 1) {
            int[] result = new int[2];

            if (pivot > input[0]) {
                result[0] = input[0];
                result[1] = pivot;
            }
            else {
                result[0] = pivot;
                result[1] = input[0];
            }
            return result;
        }
        int[] right = new int[maxIndex];
        int rightCounter = -1;
        int leftCounter = -1;
        int[] left = new int[maxIndex];
        for (int i = 0; i < maxIndex; i++) {
            if (input[i] <= pivot) {
                left[++leftCounter] = input[i];
            }
            else {
                right[++rightCounter] = input[i];
            }
        }

        int[] leftResult = leftCounter > -1 ? quickSortWithPartition(left, leftCounter) : null;
        int[] rightResult = rightCounter > -1 ? quickSortWithPartition(right, rightCounter) : null;
        int[] result = prepareResult(leftResult, leftCounter, pivot, rightResult, rightCounter);
        return result;
    }


}
