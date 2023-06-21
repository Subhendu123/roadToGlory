package com.roadtoglory.ds;

public class MergeSort implements Sort{
    /**
     * @param input
     * @return
     */
    @Override
    public int[] sort(int[] input) {

        callSplitArray(input);
        return input;
    }

    private void mergeSortedArray(int[] leftArr, int[] rightArr, int[] input) {

        int i= 0, j=0 , k =0;
        while (i < leftArr.length && j < rightArr.length){
            if(leftArr[i] <= rightArr[j]){
                input[k] = leftArr[i];
                i++;
                k++;
            }
            else {
                input[k] = rightArr[j];
                j++;
                k++;
            }

        }
        while (i <leftArr.length){
            input[k] = leftArr[i];
            k++;
            i++;
        }
        while (j < rightArr.length){
            input[k] = rightArr[j];
            k++;
            j++;
        }


    }

    private void callSplitArray(int[] input) {

        if(input.length < 2) {
            // end of the split operation
            return;
        }
        int mid = input.length/ 2;
        int[] leftArr = new int[mid];
        int[] rightArr = new int[input.length - mid];

        for(int i=0; i<mid; i++)
            leftArr[i] = input[i];
        for(int j=mid; j<input.length; j++){
            rightArr[j-mid] = input[j];
        }

        callSplitArray(leftArr);
        callSplitArray(rightArr);

        mergeSortedArray(leftArr, rightArr, input);
    }
}
