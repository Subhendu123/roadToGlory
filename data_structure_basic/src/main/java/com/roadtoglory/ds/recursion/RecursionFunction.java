package com.roadtoglory.ds.recursion;

/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.ds.recursion
   @Author Subhendu
   @Date 11-Sep-2024 07:46
*
*
*/
public class RecursionFunction
{


    public void recur (int n)
    {
        if (n > 0)
        {
            System.out.println("Tail Recursion (before recursive call execution) " + n);
            recur(n - 1);
            System.out.println("Head Recursion (after recursive call execution) " + n);
        }
    }


}
