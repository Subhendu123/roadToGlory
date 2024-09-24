package com.roadtoglory.ds.testing;

import java.util.Random;


/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.ds.testing
   @Author Subhendu
   @Date 29-Aug-2024 07:49
*
*
*/
public class StackMemoryExample
{


    public static void main (String[] args)
    {
        int randomNumber = getRandomNumber();
        System.out.println("The random number is " + randomNumber);
        int modifiedRN = modifyNumber(randomNumber);
        System.out.println("The modified number is " + modifiedRN);
        int finalNumber = calculateNumber(modifiedRN);
        System.out.println("The calculate number is " + finalNumber);
    }

    private static int calculateNumber (int modifiedRN)
    {
        return modifiedRN > 0 ? modifiedRN * 2 : modifiedRN * 4;
    }

    private static int modifyNumber (int randomNumber)
    {
        return randomNumber > 0 ? randomNumber * 5 : randomNumber / 2;
    }

    private static int getRandomNumber ()
    {
        Random random = new Random();
        return random.nextInt(5);
    }


}
