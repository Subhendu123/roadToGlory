package com.roadtoglory.ds.testing;

import com.roadtoglory.ds.linkedlists.LinkedListImpl;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class LinkedListLeetCodeTesting
{

    public static void main(String[] args)
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
