package com.roadtoglory.ds.sorting;


public class BubbleSort implements Sort
{

    public BubbleSort ()
    {
        System.out.println("************* This is a Bubble Sort ************");
    }

    public int[] sort (int[] inpArray)
    {

        for (int i = 0; i < inpArray.length; i++)
        {
            for (int j = 0; j < inpArray.length - i - 1; j++)
            {
                if (inpArray[j] > inpArray[j + 1])
                {
                    ArrayUtility.swapByIndices(inpArray, j, j + 1);
                }
            }
        }
        return inpArray;
    }

    public int[] sort2 (int[] inpArray)
    {

        for (int unsortedIndex = inpArray.length - 1; unsortedIndex > 0; unsortedIndex--)
        {
            for (int i = 0; i < unsortedIndex; i++)
            {
                if (inpArray[i] > inpArray[i + 1])
                {
                    ArrayUtility.swapByIndices(inpArray, i, i + 1);
                }
            }
        }
        return inpArray;
    }
}
