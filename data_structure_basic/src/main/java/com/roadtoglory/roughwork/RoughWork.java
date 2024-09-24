package com.roadtoglory.roughwork;

/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.roughwork
   @Author Subhendu
   @Date 04-Jul-2024 21:59
*
*
*/
public class RoughWork
{


    public static void main (String[] args)
    {
        String num1 = "1234";
        boolean isNegative = false;
        for (char c : num1.toCharArray())
        {

            int digit_ascii = (int) c;
            if (digit_ascii == 46)
            {
                isNegative = true;
            }
            int digit = digit_ascii - 48;

            System.out.println((int) c);
        }
    }


}
