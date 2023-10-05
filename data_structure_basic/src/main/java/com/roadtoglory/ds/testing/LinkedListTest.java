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
*/
public class LinkedListTest
{

    public static void main(String[] args)
    {
        LinkedListImpl linkedListImpl = new LinkedListImpl(10);
//        linkedListImpl.printList();
        linkedListImpl.append(20);
//        linkedListImpl.printList();
        linkedListImpl.append(30);
//        linkedListImpl.printList();
        linkedListImpl.append(40);
//        linkedListImpl.printList();
        linkedListImpl.append(50);
//        linkedListImpl.printList();
        linkedListImpl.addWithIndex(38,4);
        linkedListImpl.printList();

        linkedListImpl.removeFirst();
        linkedListImpl.printList();

        linkedListImpl.removeLast();
        linkedListImpl.printList();

        linkedListImpl.getLength();
    }
}
