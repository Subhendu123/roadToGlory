package com.roadtoglory.ds.testing;

import com.roadtoglory.ds.hashtable.Hashtable;
import com.roadtoglory.ds.linkedlists.DoublyLinkedListImpl;
import com.roadtoglory.ds.linkedlists.LinkedListImpl;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class DLLTesting
{
    public static void main (String[] args)
    {
        DoublyLinkedListImpl doublyLinkedList = new DoublyLinkedListImpl(10);
        doublyLinkedList.append(20);
        doublyLinkedList.append(30);
        doublyLinkedList.append(40);
        doublyLinkedList.append(50);
        doublyLinkedList.prepend(5);
        doublyLinkedList.prepend(2);
        doublyLinkedList.removeLast();
        doublyLinkedList.printList();

        doublyLinkedList.addWithIndex(44, 4);
        System.out.println("\n Add with index is called!");
        doublyLinkedList.printList();

        System.out.println("\n Reverse:");
        doublyLinkedList.reverse();


    }

    /*
        *
        *
        *
                This is created by Subhendu (2023)
        *
        *
        *
        */public static class LinkedListLeetCodeTesting
    {

        public static void main (String[] args)
        {
            LinkedListImpl myLinkedList = new LinkedListImpl(1);
            myLinkedList.append(2);
            myLinkedList.append(3);
            myLinkedList.append(2);
            myLinkedList.append(5);

            // create a loop by connecting the tail to the second node
            //        myLinkedList.getTail().next = myLinkedList.getHead().next;
            myLinkedList.printList();
            myLinkedList.removeDuplicates();
            myLinkedList.printList();
        }
    }

    public static class HTTesting
    {
        public static void main (String[] args)
        {
            Hashtable hashtable = new Hashtable();
            hashtable.insert("A", 10);
            hashtable.insert("B", 20);
            hashtable.insert("C", 12);
            hashtable.insert("D", 14);
            hashtable.insert("E", 15);
            hashtable.insert("F", 34);
            hashtable.printTable();
        }
    }
}
