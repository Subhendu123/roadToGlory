package com.roadtoglory.ds.linkedlists;

/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.ds.linkedlists
   @Author Subhendu
   @Date 07-Nov-2024 06:54
*
*
*/
public class LinkedListUtil
{


    public static void merge (LinkedListImpl list1, LinkedListImpl list2)
    {
        Node node = mergeSortedLists(list1.getHead(), list2.getHead());
        System.out.println("Printing the node");
        while (node != null)
        {
            System.out.print(node.value + " --> ");
            node = node.next;
        }
    }


    private static Node mergeSortedLists (Node listhead1, Node listhead2)
    {
        Node ptr3 = null;
        while (listhead1 != null && listhead2 != null)
        {
            Node big = listhead2;
            Node small = listhead1;
            if (listhead1.value > listhead2.value)
            {
                big = listhead1;
                small = listhead2;
            }
            if (ptr3 == null)
            {
                // this is the first node
                ptr3 = small;
            }
            else
            {
                ptr3.next = small;
                ptr3 = ptr3.next;
            }
            ptr3.next = big;
            ptr3 = ptr3.next;

            listhead1 = listhead1.next;
            listhead2 = listhead2.next;

        }
        return ptr3;
    }


}
