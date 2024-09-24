package com.roadtoglory.ds.search;

/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.ds.search
   @Author Subhendu
   @Date 19-Aug-2024 07:33
*
*
*/
public class BinarySearch
{


    private int[] input;


    public int search (int item)
    {
        int low = 0;
        int high = this.input.length - 1;

        while (low <= high)
        {
            int mid = (low + high) / 2;
            int guess = this.input[mid];
            if (guess > item)
            {
                // new mid or guess element is larger than the item to be searched. So, search the left array not the right array
                high = mid - 1;
                // why mid -1, cuz already checked the mid position
            }
            else if (guess < item)
            {
                low = mid + 1;
                // why mid + 1, cuz already checked the mid position
            }
            else
            {
                return mid;
            }
        }
        return -1;
    }

    public int[] getInput ()
    {
        return input;
    }

    public void setInput (int[] input)
    {
        this.input = input;
    }


}
