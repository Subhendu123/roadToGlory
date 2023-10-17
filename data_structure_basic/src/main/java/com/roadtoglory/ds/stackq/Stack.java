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
    public Node base;
    public Node top;
    public int length;

    public Stack (int value)
    {
        if (this.base == null)
        {
            this.base = new Node(value);
            this.top = this.base;
            this.length++;
        }
    }

    public int push (int value)
    {
        if (this.base == null)
        {
            return -1;
        } else
        {
            Node node = new Node(value);
            this.top.next = node;
            this.top = this.top.next;
            this.length++;
            return this.length;
        }
    }

    public int pop ()
    {
        if (this.base == null)
        {
            return -1;
        } else
        {
            Node tempItr = this.base;

            while (tempItr != null)
            {
                if (tempItr.next == this.top)
                {
                    tempItr.next = null;
                    this.top = tempItr;
                    this.length--;
                    break;
                }
                tempItr = tempItr.next;
            }
            return this.length;
        }
    }

    public void printStack ()
    {
        Node node = this.base;
        while (node != null)
        {
            System.out.println("|");
            System.out.println(node.value);
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
