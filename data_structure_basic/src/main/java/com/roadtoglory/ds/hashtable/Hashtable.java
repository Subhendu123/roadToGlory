package com.roadtoglory.ds.hashtable;

/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class Hashtable
{
    public static final int size = 10;
    public Node[] node;

    public Hashtable ()
    {
        this.node = new Node[size];
    }

    public int hash (int value)
    {
        return value % size;
    }

    public void insert (String key, int value)
    {
        int index = hash(value);
        if (this.node[index] == null)
        {
            Node newNode = new Node(key, value);
            this.node[index] = newNode;
        }
        else
        {
            Node nodeItr = this.node[index];
            Node nodeHead = nodeItr;
            while (nodeItr != null)
            {
                if (nodeItr.next == null)
                {
                    Node newNode = new Node(key, value);
                    nodeItr.next = newNode;
                    break;
                }
                nodeItr = nodeItr.next;
            }
            this.node[index] = nodeHead;
        }
    }

    public Node search (String key, int value)
    {
        int index = hash(value);
        if (this.node[index] != null)
        {
            Node itr = this.node[index];
            while (itr != null)
            {
                if (itr.value == value && itr.key == key)
                {
                    return itr;
                }
                itr = itr.next;
            }
        }
        return null;
    }

    public boolean deleteByElement (String key, int value)
    {
        int index = hash(value);
        if (this.node[index] != null)
        {
            Node itr = this.node[index];
            Node headNode = itr;
            if (itr.next != null)
            {
                while (itr != null)
                {
                    if (itr.next.value == value && itr.next.key == key)
                    {
                        itr.next = null;
                        return true;
                    }
                    itr = itr.next;
                }
            }
            else
            {
                if (itr.value == value && itr.key == key)
                {
                    itr = null;
                    this.node[index] = null;
                }
            }
        }
        return false;
    }


    public void printTable ()
    {
        for (int i = 0; i < size; i++)
        {
            Node currNode = this.node[i];
            System.out.print("Index " + i + ": ");
            boolean countMoreThan1 = false;
            while (currNode != null)
            {
                if (countMoreThan1)
                {
                    System.out.print(" -->");
                }
                System.out.print("\t" + "{ " + currNode.key + ":" + currNode.value + "}");
                currNode = currNode.next;
                countMoreThan1 = true;
            }
            System.out.println();
        }
    }

    public class Node
    {
        public String key;
        public int value;
        public Node next;

        public Node (String key, int value)
        {
            this.key = key;
            this.value = value;
        }
    }


}