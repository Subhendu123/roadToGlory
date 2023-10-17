package com.roadtoglory.ds.testing;

import com.roadtoglory.ds.linkedlists.DoublyLinkedListImpl;


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
    public static void main(String[] args)
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
}
