package com.roadtoglory.ds;

public class SelectionSort {

    public static void main(String[] args) {
        int[] input = {0,-2,4,10,-5, 3, 9};
        System.out.println("The Input array before sorting is ");
        ArrayUtility.printArray(input);

        int[] sortedInput = new SelectionSort().sort(input);

        System.out.println("The Selection sorted array is ");
        ArrayUtility.printArray(sortedInput);

        int[] input1 = {0,20, 100, 80, 50, 70, 60, 30, 90 , 10, 40};
        System.out.println("The Input array before sorting is ");
        ArrayUtility.printArray(input1);

        int[] sortedInput1 = new SelectionSort().sort(input1);

        System.out.println("The bubble sorted array is ");
        ArrayUtility.printArray(sortedInput1);
    }

    public int[] sort(int[] inpArr){
        int largest = 0;
        int largestIndexUnsorted = -1;
        for(int lastUnsortedIndex = inpArr.length-1;lastUnsortedIndex > 0; lastUnsortedIndex--){

            largest = inpArr[0];
            largestIndexUnsorted=0;


            for(int i=1; i<=lastUnsortedIndex;i++){

                if(largest < inpArr[i]){
                    largest = inpArr[i];
                    largestIndexUnsorted=i;
                }
                if(i == lastUnsortedIndex){
                    ArrayUtility.swapByIndices(inpArr, i, largestIndexUnsorted);
                }
            }
        }

        return inpArr;
    }
}
