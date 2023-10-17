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
        System.out.println(stack.push(20));
        System.out.println(stack.push(30));
        System.out.println(stack.push(40));
        stack.push(50);
        System.out.println(stack.pop());
        stack.printStack();
    }
}
