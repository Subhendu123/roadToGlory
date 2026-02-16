/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.basics;

import com.roadtoglory.ds.recursion.RecursionExamples;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.ds.testing
   @Author Subhendu
   @Date 11-Sep-2024 07:48
*
*
*/
public class RecursionTesting {


    private static Logger LOG = LogManager.getLogger(RecursionTesting.class);

    public static void main(String[] args) {
        LOG.info("Starting the recursion testing...");
        //        RecursionFunction function = new RecursionFunction();
        //        function.recur(3);
        RecursionExamples examples = new RecursionExamples();
        LOG.debug("Sum of 5 natural no.s: " + examples.sumOfnNaturalNumbers(5));
        LOG.debug("The factorial of 5: " + examples.factorial(5));
        long t = System.currentTimeMillis();
        LOG.debug("5 to the power 10 is: " + examples.pow(5, 10));
        LOG.debug("2 to the power 20 is: " + examples.pow(2, 20));
        t = System.currentTimeMillis() - t;
        LOG.debug("Total time taken (power function) is " + t);
        LOG.debug("The taylor series is " + examples.taylorSeries(3, 10));
        LOG.debug("The taylor series (improved version) is " + examples.imTaylorSeries(3, 10));
        LOG.debug("The taylor series (iterative version) is " + examples.imTaylorSeriesIterative(3, 10));
        LOG.debug("The fibonacci series " + examples.fibonacciMemoization(5));
        LOG.debug("The nCr: 5c3 Value is " + examples.getNcR(5, 3));
        LOG.debug("The nCr with Recursion: 5c3 Value is " + examples.nCrWithRecursion(5, 3));
        LOG.debug("The tower of hanoi problem for 3 disks is: ");
        examples.towerOfHanoi(1, "Source", "Aux", "Destination");
    }


}
