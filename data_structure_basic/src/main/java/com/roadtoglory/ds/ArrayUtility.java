package com.roadtoglory.ds;

public class ArrayUtility {

    public static void printArray(int[] inputArray){
        for (int j : inputArray) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void swapElements(int[] inputArray, int index1, int index2){
        if(index1!=index2){
            int tempVarForSwap = inputArray[index2];
            inputArray[index2] = inputArray[index1];
            inputArray[index1] = tempVarForSwap;
        }
    }

}
