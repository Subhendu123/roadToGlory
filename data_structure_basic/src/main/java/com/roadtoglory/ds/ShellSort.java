package com.roadtoglory.ds;

public class ShellSort implements Sort{
    public ShellSort() {
        System.out.println("**************** Shell Sort *************");
    }

    /**
     * @param input
     * @return
     */
    @Override
    public int[] sort(int[] input) {

        int gap = input.length / 2;

        for(int i=gap;i<input.length;i++){
            int replaceIndex = -1;
            int newElement = input[i];
            for (int j=i-gap; j > -1; j=j-gap){
                if(input[j] > newElement){
                    input[i] = input[j];
                    replaceIndex = j;
                }
                if(j-gap<0 && replaceIndex > -1){
                    input[replaceIndex] = newElement;
                }
            }
            if(i == input.length - 1){
                gap = Math.max(gap / 2, 1);
                i = gap;
            }
            if(gap == 1){
                System.out.println("Before passing to insertion");
                ArrayUtility.printArray(input);
                input = new InsertionSort().sort(input);
                break;
            }
        }


        return input;
    }
}
