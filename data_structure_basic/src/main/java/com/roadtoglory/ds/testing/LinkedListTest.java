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
    }
}
