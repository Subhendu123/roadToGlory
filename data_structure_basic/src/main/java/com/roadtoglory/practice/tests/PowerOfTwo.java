package com.roadtoglory.practice.tests;

/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.practice.tests
   @Author Subhendu
   @Date 12-Sep-2024 18:06
*
*
*/
public class PowerOfTwo
{


    public static void main (String[] args)
    {
        int n = 2147483647;
        boolean isPower = isPowerOfTwo(n);
        System.out.println("Is it power of 2? " + isPower);
    }

    public static boolean isPowerOfTwo (int n)
    {

        if (n == 1)
        {
            return true;
        }
        if (n % 2 != 0)
        {
            return false;
        }

        int startIndex = 1;
        if (n > 1048576)
        {

            startIndex = 20;
        }
        if (n > 1073741824)
        {
            startIndex = 30;
        }

        for (int i = startIndex; i < 32; i++)
        {
            int val = twoToThePower(i);

            //            int val = (int) Math.pow(2, i);
            if (Math.abs(val) == Math.abs(n))
            {
                return true;
            }
            else if (Math.abs(val) > Math.abs(n))
            {
                return false;
            }
        }
        return false;
    }

    private static int twoToThePower (int power)
    {
        int result = 1;
        int i = 0;
        if (power > 10)
        {
            result = 1024;
            i = 10;
        }
        if (power > 20)
        {
            result = 1048576;
            i = 20;
        }
        if (power > 30)
        {
            result = 1073741824;
            i = 30;
        }

        for (; i < power; i++)
        {
            result = result * 2;
        }
        return result;
        //        if (power == 0)
        //        {
        //            return 1;
        //        }
        //        return 2 * twoToThePower(power - 1);
    }


}
