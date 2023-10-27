package com.roadtoglory.ds.stackq;

/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class Queue
{
    Node first;
    Node last;
    int length;

    public Queue (int value)
    {
        if (this.first == null)
        {
            this.first = new Node(value);
            this.last = first;
            this.length++;
        }
        else
        {
            add(value);
        }

    }

    public void add (int value)
    {
        if (this.last != null)
        {
            Node newNode = new Node(value);
            newNode.next = this.last;
            this.last = newNode;
            this.length++;
        }
        else
        {
            throw new RuntimeException("The Queue is empty or having some internal issue");
        }
    }

    public void remove ()
    {
        if (this.first != null)
        {
            Node tmpItr = this.last;
            Node newFirst = null;
            while (tmpItr != null)
            {
                if (tmpItr.next == this.first)
                {
                    newFirst = tmpItr;
                    break;
                }
                tmpItr = tmpItr.next;
            }
            if (newFirst != null)
            {
                System.out.println("The value to be removed is " + this.first.value);
                this.first = newFirst;
                newFirst.next = null;
            }
        }
        else
        {
            throw new RuntimeException("The Queue is empty or having some internal issue");
        }
    }

    public void printQueue ()
    {
        Node tempItr = this.last;
        int cnt = 0;
        while (tempItr != null)
        {
            if (cnt == 0)
            {
                System.out.print("last -> " + tempItr.value);
            }
            else
            {
                System.out.print(" -> " + tempItr.value);
            }

            tempItr = tempItr.next;
            cnt++;
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
