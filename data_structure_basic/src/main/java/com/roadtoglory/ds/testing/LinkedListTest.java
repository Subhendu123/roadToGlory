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

    public static void main (String[] args)
    {
        LinkedListImpl linkedListImpl = new LinkedListImpl(10);
        linkedListImpl.append(20);
        linkedListImpl.append(30);
        linkedListImpl.append(40);
        linkedListImpl.append(50);
        linkedListImpl.addWithIndex(38, 4);

        System.out.println(linkedListImpl.removeFirst().getValue());

        System.out.println(linkedListImpl.removeLast().value);
        System.out.println(linkedListImpl.removeLast().value);

        linkedListImpl.prepend(11);
        linkedListImpl.prepend(01);
        linkedListImpl.printList();

        //        linkedListImpl.removeIndexWise(4);
        //        linkedListImpl.printList();
        //        linkedListImpl.removeIndexWise(2);
        //        linkedListImpl.printList();

        linkedListImpl.reverse();

        linkedListImpl.getLength();

        System.out.println("Linked List before sending ---------------");
        LinkedListImpl linkedList = new LinkedListImpl(3);
        linkedList.append(8);
        linkedList.append(5);
        linkedList.append(10);
        linkedList.append(2);
        linkedList.append(1);
        linkedList.printList();
        linkedList.partitionList(5);


    }
}
