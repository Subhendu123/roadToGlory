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

        int[] sortedInput1 = new BubbleSort().sort2(input1);

        System.out.println("The bubble sorted array is ");
        ArrayUtility.printArray(sortedInput1);
    }

    public int[] sort(int[] inpArray){

        for(int i=0;i<inpArray.length;i++){
            for(int j=0; j< inpArray.length-i-1; j++){
                if(inpArray[j] > inpArray[j+1]) {
                    ArrayUtility.swapByIndices(inpArray, j, j+1);
                }
            }
        }
        return inpArray;
    }

    public int[] sort2(int[] inpArray){

        for(int unsortedIndex=inpArray.length-1; unsortedIndex > 0; unsortedIndex--){
            for (int i=0; i<unsortedIndex;i++){
                if(inpArray[i] > inpArray[i+1]) {
                    ArrayUtility.swapByIndices(inpArray, i, i+1);
                }
            }
        }
        return inpArray;
    }
}
