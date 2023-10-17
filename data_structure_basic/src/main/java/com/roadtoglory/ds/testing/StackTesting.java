package com.roadtoglory.ds.testing;

import com.roadtoglory.ds.stackq.Stack;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class StackTesting
{
    public static void main (String[] args)
    {
        Stack stack = new Stack(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.printStack();
        System.out.println("-------------------------------------");
        System.out.println();
        stack.pop();
        stack.pop();

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.push(44);
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println();
        stack.printStack();
    }
}
