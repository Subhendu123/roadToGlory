/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.testing.basics;

import com.roadtoglory.ds.stackq.Stack;
import com.roadtoglory.ds.stackq.StackUsingArray;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class StackTesting {


    public static void main(String[] args) {
        boolean useArray = true;
        if (useArray) {
            executeStackUsingArray();
        }
        else {
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

    public static void executeStackUsingArray() {
        StackUsingArray stack = new StackUsingArray(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.push(37);
        stack.push(23);

        stack.printStack();
        System.out.println("-------------------------------------");
        System.out.println();
        System.out.println("Peeked at index 3 " + stack.peek(3));
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
