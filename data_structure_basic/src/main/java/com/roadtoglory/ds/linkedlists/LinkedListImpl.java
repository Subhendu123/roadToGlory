package com.roadtoglory.ds.linkedlists;

import java.util.HashSet;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class LinkedListImpl implements LinkedList
{

    private Node head;
    private Node tail;
    private int length;

    public LinkedListImpl(int value){

        if(this.head == null){
            this.head = new Node(value);
            this.tail = this.head;
            this.length = 1;
        }
        else {
            append(value);
        }
    }


    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public void printList(){
        System.out.println("The print list is called!");
        Node tmp = this.head;
        while (tmp != null){
            System.out.print(tmp.value +" --> ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public void getLength(){
        System.out.println("Length "+this.length);
    }
    public void append(int value){
        Node newNode = new Node(value);
        if(tail != null){
            tail.next = newNode;
            tail = tail.next;
            this.length++;
        }
        else {
            System.out.println("Validation Failed! Please create a linkedlist first!");
        }
    }

    /**
     * @param value
     */
    @Override
    public void prepend(int value)
    {

        Node newHead = new Node(value);
        newHead.next= this.head;
        this.head = newHead;
        this.length++;

    }

    public void addWithIndex(int value, int index){
        System.out.println("Adding "+value+" to the index no. "+index);

        Node tempNode = this.head;
        int tempIndex = 1;
        while (tempNode != null){
            if(tempIndex == index-1){
                Node newNode = new Node(value);
                Node tNode = tempNode.next;
                newNode.next = tNode;
                tempNode.next = newNode;
                this.length++;
                break;
            }
            tempIndex++;
            tempNode = tempNode.next;
        }
    }

    /**
     * @return
     */
    @Override
    public Node removeFirst()
    {
        Node head = this.head;
        this.head = this.head.next;
        this.length--;
        head.next=null;
        return head;
    }

    /**
     * @return
     */
    @Override
    public Node removeLast()
    {
        Node head = this.head;
        Node res = null;
        while (head.next != null){
            if(head.next == this.tail){
                res =  this.tail;
                head.next = null;
                this.tail = head;
                this.length--;
                break;
            }
            head = head.next;
        }

        return res;
    }

    /**
     * @param index
     *
     * @return
     */
    @Override
    public int removeIndexWise(int index)
    {
        int retCode = -1;
        Node itr = this.head;
        if(index > this.length){
            throw new RuntimeException("Please check the passed index "+index+" which is more than the total length "+this.length);
        }
        else if(index < 1){
            throw new RuntimeException("Please select a correct input");
        }
        else {
            int itrCount = 1;
            while (itr != null){
                if(index==1){
                    removeFirst();
                    break;
                } else if (index == this.length)
                {
                    removeLast();
                    break;
                }
                else if(itrCount == index-1){
                    Node tempDlt = itr.next;
                    itr.next=tempDlt.next;
//                    tempDlt = null;
                    retCode = 1;
                    this.length--;
                    break;
                }
                itr = itr.next;
                itrCount++;
            }
        }
        return retCode;

    }

    /**
     * @param index
     * @param value
     */
    @Override
    public void replaceIndex(int index, int value)
    {

    }

    /**
     * @param index1
     * @param index2
     */
    @Override
    public void substitute(int index1, int index2)
    {

    }

    /**
     *
     */
    @Override
    public void reverse()
    {
        if(this.head == null || this.tail == null || this.length == 0)
            System.out.println("Invalid! Please create a linkedlist before calling this method");
        else {
            Node temp = this.head;
            this.head = this.tail;
            this.tail = temp;
            Node after = temp.next;
            Node before = null;
            while (after != null){
                after = temp.next;
                temp.next = before;
                before = temp;
                temp = after;
            }
            System.out.println("The reverse is");
            printList();
        }

    }


    public boolean hasLoop()
    {

        boolean isLoopPresent = false;
        Node temp = this.head;
        int index = 1;
        while(index < this.length && temp != null){

            Node itr = temp.next;
            int localndex = 1;
            while(localndex < this.length && itr != null){
                if(itr.next == temp){
                    isLoopPresent = true;
                    break;
                }
                itr = itr.next;
                localndex++;
            }

            if(isLoopPresent)
                break;
            temp = temp.next;
            index++;

        }

        return isLoopPresent;

    }

    public void partitionList(int x){

        Node smallNode = null;
        Node largeNode = null;
        Node largeHead = null;
        if(this.length < 2)
            return;

        Node itr = this.head;
        while(itr != null){
            if(itr.value < x){
                if(smallNode == null)
                {
                    smallNode = itr;
                    smallNode = smallNode.next;
                }
                else
                {
                   smallNode.next = itr;
                   smallNode = smallNode.next;
                }
            }
            else
            {
                if(largeNode == null)
                {
                    largeNode = itr;
                    largeHead = largeNode;
                    largeNode = largeNode.next;

                }
                else{
                    largeNode.next = itr;
                    largeNode = largeNode.next;
                }
            }
            itr = itr.next;
        }
        if(smallNode != null){
            Node xNode = new Node(x);
            smallNode.next = xNode;
            smallNode = smallNode.next;
            smallNode.next = largeHead;
            this.head = smallNode;
        }
        printList();
    }


    public void removeDuplicates() {
        // Your implementation goes here

        if(this.head == null)
            return;

        Node next = this.head.next;
        Node prev = this.head;
        HashSet<Integer> hashSetNode = new HashSet<>();
        hashSetNode.add(this.head.value);
        boolean isDuplicatePresent = false;
        while(next != null){

            if(hashSetNode.contains(next.value)){
                System.out.println("prev val "+prev.value);
                prev.next = next.next;
                isDuplicatePresent = true;
            }
            else {
                System.out.println("prev val as hashnode "+prev.value);

                hashSetNode.add(prev.value);
            }
            prev = prev.next;
            next = next.next;
        }
        if(isDuplicatePresent && hashSetNode.size() > 1)
            this.head = prev;

        Node tmpHd = prev;
        System.out.println("The new list is ");
        while (tmpHd != null){
            System.out.println(tmpHd+" -> ");
        }

    }

}
