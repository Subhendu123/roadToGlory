package com.roadtoglory.ds;

public class InsertionSort implements Sort{

    public InsertionSort(){
        System.out.println("************* This is a Insertion Sort ************");
    }


    public int[] sort(int[] input){

        for(int elementSortingIndex= 1; elementSortingIndex<input.length; elementSortingIndex++){
            int sortingElement = 0;
            int replacableIndex = -1;
            sortingElement = input[elementSortingIndex];

            for(int sortedPartIndex = elementSortingIndex-1; sortedPartIndex > -1; sortedPartIndex--){

                if(sortingElement < input[sortedPartIndex]){
                    input[sortedPartIndex+1] = input[sortedPartIndex];
                    replacableIndex = sortedPartIndex;
                }

                if(sortedPartIndex == 0 && replacableIndex > -1){
                    // all the sorted array checked
                    input[replacableIndex] = sortingElement;
                }
            }
        }

        return input;

    }
}
