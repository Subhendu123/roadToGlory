package com.roadtoglory.ds;

public class BubbleSort {

    public static void main(String[] args) {

        int[] input = {0,-2,4,10,-5, 3, 9};
        System.out.println("The Input array before sorting is ");
        ArrayUtility.printArray(input);

        int[] sortedInput = new BubbleSort().sort(input);

        System.out.println("The bubble sorted array is ");
        ArrayUtility.printArray(sortedInput);

        int[] input1 = {0,20, 100, 80, 50, 70, 60, 30, 90 , 10, 40};
        System.out.println("The Input array before sorting is ");
        ArrayUtility.printArray(input1);

        int[] sortedInput1 = new BubbleSort().sort(input1);

        System.out.println("The bubble sorted array is ");
        ArrayUtility.printArray(sortedInput1);
    }

    public int[] sort(int[] inpArray){

        for(int i=0;i<inpArray.length;i++){
            for(int j=0; j< inpArray.length-i-1; j++){
                if(inpArray[j] > inpArray[j+1]) {
                    int tempVarForSwap = inpArray[j + 1];
                    inpArray[j + 1] = inpArray[j];
                    inpArray[j] = tempVarForSwap;
                }
            }
        }
        return inpArray;

    }
}
