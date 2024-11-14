package com.roadtoglory.ds.stackq;

/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.ds.stackq
   @Author Subhendu
   @Date 14-Nov-2024 07:06
*
*
*/
public class StackUsingArray
{


    private int[] stack = null;
    private int top = -1;
    private int totalSize = -1;

    public StackUsingArray (int size)
    {
        stack = new int[size];
        totalSize = size;
    }

    public void push (int element)
    {
        if (this.top >= this.totalSize)
        {
            throw new ArithmeticException("The Stack is full.");
        }
        stack[++this.top] = element;
        System.out.println("The element which has been pushed is " + element + " and the top is " + this.top);
    }

    public int pop ()
    {
        if (this.top == -1)
        {
            throw new ArithmeticException("The Stack is empty.");
        }
        int retVal = stack[this.top--];
        System.out.println("The element which has been popped is " + retVal);
        return retVal;
    }

    public int peek (int index)
    {
        int val = -1;
        for (int i = this.top; i >= index; i--)
        {
            val = this.stack[i];
        }

        return val;
    }

    public void printStack ()
    {
        System.out.println();
        for (int i = this.top; i > -1; i--)
        {
            int val = this.stack[i];
            System.out.print(val + " -> ");
        }
    }


}
