package com.roadtoglory.ds;

public class FactorialKlass {
    public static void main(String[] args) {

        System.out.println("Factorial of a number "+ new FactorialKlass().fact(3));

    }

    public int fact(int n){
        if(n == 0)
            return 1;
        else
            return n * fact(n-1);
    }
}
