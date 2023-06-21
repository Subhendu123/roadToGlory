package com.roadtoglory.ds;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Sort sortingKlass = new MergeSort();

        int[] input = new int[100000000];
        Random random = new Random();
        for(int i=0; i<input.length; i++){
            input[i] = random.nextInt();
        }
        System.out.println("The Input array before sorting is ");
        ArrayUtility.printArray(input);

        int[] sortedInput = sortingKlass.sort(input);

        System.out.println("The Sorted array is ");
        ArrayUtility.printArray(sortedInput);

/*
        int[] input1 = {0,20, 100, 80, 50, 70, 60, 30, 90 , 10, 40 , -5};
        System.out.println("The Input array before sorting is ");
        ArrayUtility.printArray(input1);

        int[] sortedInput1 = sortingKlass.sort(input1);

        System.out.println("The sorted array is ");
        ArrayUtility.printArray(sortedInput1);*/
    }
}