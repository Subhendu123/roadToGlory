package com.roadtoglory.ds.testing;

import com.roadtoglory.ds.search.BinarySearch;


/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.ds.testing
   @Author Subhendu
   @Date 19-Aug-2024 08:30
*
*
*/
public class BinarySearchTesting
{


    public static void main (String[] args)
    {
        BinarySearch binarySearch = new BinarySearch();
        int[] input = {10, 20, 25, 30, 40, 50, 55, 60};
        binarySearch.setInput(input);
        int position = binarySearch.search(56);
        System.out.println("The binary search algorithm has found your input in the position " + position);
    }


}
