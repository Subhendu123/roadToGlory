package com.roadtoglory.ds;

public class Main {
    public static void main(String[] args) {

        Sort sortingKlass = new SelectionSort();

        int[] input = {0,-2,4,10,-5, 3, 9};
        System.out.println("The Input array before sorting is ");
        ArrayUtility.printArray(input);

        int[] sortedInput = sortingKlass.sort(input);

        System.out.println("The Sorted array is ");
        ArrayUtility.printArray(sortedInput);

        int[] input1 = {0,20, 100, 80, 50, 70, 60, 30, 90 , 10, 40 , -5};
        System.out.println("The Input array before sorting is ");
        ArrayUtility.printArray(input1);

        int[] sortedInput1 = sortingKlass.sort(input1);

        System.out.println("The sorted array is ");
        ArrayUtility.printArray(sortedInput1);
    }
}