package com.roadtoglory.ds.stackq;

/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.ds.stackq
   @Author Subhendu
   @Date 23-Nov-2024 07:18
*
*
*/
public class StackUsingLL
{


    private Node top;


    public StackUsingLL (int nodeVal)
    {
        if (this.top == null)
        {
            // this is the first node
            this.top = new Node(nodeVal);
        }
        else
        {
            Node newTop = new Node(nodeVal);
            newTop.next = this.top;
            
        }
    }


    private class Node
    {


        Node next;
        int value;

        public Node (int value)
        {
            this.value = value;
        }


    }


}
