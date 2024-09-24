package com.roadtoglory.ds.recursion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.ds.recursion
   @Author Subhendu
   @Date 16-Sep-2024 07:25
*
*
*/
public class RecursionExamples
{


    private static double sum = 0;
    private static double sum_ts = 1;
    private Logger LOG = LogManager.getLogger(RecursionExamples.class);
    private int[] resFib = new int[10];

    public int sumOfnNaturalNumbers (int n)
    {
        LOG.info("Adding the Sum of n natural numbers recursively.");
        if (n == 0)
        {
            return 0;
        }
        else
        {
            return sumOfnNaturalNumbers(n - 1) + n;
        }

    }

    public int factorial (int n)
    {

        LOG.info("INFO MSG - Factorial() is invoked");
        LOG.debug("DEBUG MSG");
        if (n == 0)
        {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public int pow (int n, int power)
    {
        if (power == 0)
        {
            return 1;
        }
        if (power % 2 == 0)
        {
            return pow(n * n, power / 2);
        }
        return n * pow(n * n, (power - 1) / 2);
    }

    public double taylorSeries (int x, int n)
    {
        if (x == 0 || n == 0)
        {
            return 1 + sum;
        }
        sum = sum + (double) pow(x, n) / factorial(n);
        return taylorSeries(x, n - 1);
    }

    public double imTaylorSeries (int x, int n)
    {
        if (n == 0)
        {
            return sum_ts;
        }
        sum_ts = sum_ts * x / n + 1;
        return imTaylorSeries(x, n - 1);
    }

    public double imTaylorSeriesIterative (int x, int n)
    {
        double result = 1;
        for (; n > 0; n--)
        {
            double calc = x / n;

            result = 1 + result * calc;
        }
        return result;
    }

    public int fibonacciMemoization (int n)
    {
        if (n <= 1)
        {
            resFib[n] = n;
            return n;
        }
        if (resFib[n - 2] == -1)
        {
            resFib[n - 2] = fibonacciMemoization(n - 2);
        }
        if (resFib[n - 1] == -1)
        {
            resFib[n - 1] = fibonacciMemoization(n - 1);
        }
        return resFib[n - 2] + resFib[n - 1];

    }

    // n! / (n-r)! r!
    public int getNcR (int n, int r)
    {
        if (n > r && n > 0)
        {
            return factorial(n) / (factorial(r) * factorial(n - r));
        }
        throw new ArithmeticException("Please select a valid number.");

    }

    public int nCrWithRecursion (int n, int r)
    {
        if (n < r)
        {
            throw new ArithmeticException("n cannot be larger than r");
        }
        if (n == r || r == 0)
        {
            return 1;
        }
        if (n > r && r == 1)
        {
            return n;
        }

        return nCrWithRecursion(n - 1, r - 1) + nCrWithRecursion(n - 1, r);

    }

    public void towerOfHanoi (int disk, String source, String aux, String dest)
    {
        if (disk > 0)
        {
            towerOfHanoi(disk - 1, source, dest, aux);
            LOG.info("Disk #" + disk + " is moved from " + source + " to " + dest);
            towerOfHanoi(disk - 1, aux, source, dest);
        }
    }


}
