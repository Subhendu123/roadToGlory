package com.roadtoglory.ds;

public class SelectionSort  implements Sort{

    public SelectionSort(){
        System.out.println("************* This is a Selection Sort ************");
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
