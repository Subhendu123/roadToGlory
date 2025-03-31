package com.roadtoglory.ds.testing;


import com.roadtoglory.ds.linkedlists.LinkedListImpl;
import com.roadtoglory.ds.linkedlists.LinkedListUtil;


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
        LinkedListImpl linkedListImpl = new LinkedListImpl(60);
        linkedListImpl.append(23);
        linkedListImpl.append(10);
        linkedListImpl.append(13);
        linkedListImpl.append(40);
        linkedListImpl.append(50);
        linkedListImpl.append(35);
        linkedListImpl.insert(38, 4);


        System.out.println("The node searched is found at position " + linkedListImpl.search(11));
        System.out.println("Let's try the sort. Before sorting check the linked list");
        linkedListImpl.printList();
        linkedListImpl.sort();
        System.out.println("The sorting done. please check the res.");
        linkedListImpl.printList();
        System.out.println("--------------------------------");

        LinkedListImpl linkedListImpl2 = new LinkedListImpl(60);
        linkedListImpl2.append(1);
        linkedListImpl2.append(10);
        linkedListImpl2.append(2);
        linkedListImpl2.append(8);
        linkedListImpl2.append(5);
        linkedListImpl2.append(35);
        linkedListImpl2.sort();

        LinkedListUtil.merge(linkedListImpl, linkedListImpl2);


        // insert into the sorted list

        linkedListImpl.insertIntoSortedList(12);
        System.out.println("The insert into sorted list is done. please check the linked list now.");
        linkedListImpl.printList();
        System.out.println("--------------------------------");

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
        System.out.println("reverse with 3 ptr...");
        linkedListImpl.reverseWith3Ptr();
        linkedListImpl.printList();

        linkedListImpl.getLength();

        System.out.println("Linked List before sending ---------------");
        LinkedListImpl linkedList = new LinkedListImpl(3);
        linkedList.append(8);
        linkedList.append(5);
        linkedList.append(10);
        linkedList.append(2);
        linkedList.append(1);
        linkedList.printList();
        //        linkedList.partitionList(5);


    }


}
