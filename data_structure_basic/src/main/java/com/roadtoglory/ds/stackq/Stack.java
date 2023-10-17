package com.roadtoglory.ds.stackq;

/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class Stack
{
    public Node top;
    public int length;

    public Stack (int value)
    {
        if (this.top == null)
        {
            this.top = new Node(value);
            this.length++;
        }
    }

    public int push (int value)
    {
        if (this.top == null)
        {
            System.out.println("Please create a stack before pushing");
            return -1;
        } else
        {
            Node topNode = new Node(value);
            topNode.next = this.top;
            this.top = topNode;
            this.length++;
            return this.length;
        }
    }

    public int pop ()
    {
        if (this.top == null)
        {
            return -1;
        } else
        {
            this.top = this.top.next;
            return this.length;
        }
    }

    public void printStack ()
    {
        Node node = this.top;
        if (node == null)
        {
            System.out.println("The stack is empty!");
        }
        while (node != null)
        {
            System.out.print("|-------|\n");

            System.out.println("\t" + node.value);
            System.out.println("|-------|");
            node = node.next;
        }
    }

    public class Node
    {
        Node next;
        int value;

        public Node (int value)
        {
            this.value = value;
        }
    }
}
