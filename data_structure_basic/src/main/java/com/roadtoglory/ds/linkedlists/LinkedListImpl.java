package com.roadtoglory.ds.linkedlists;

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

    private static Node head;
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
    public int removeFirst()
    {
        Node head = this.head;
        this.head = this.head.next;
        int res = head.value;
        head = null;
        return res;
    }

    /**
     * @return
     */
    @Override
    public int removeLast()
    {
        Node head = this.head;
        int res = -1;
        while (head.next != null){
            if(head.next == this.tail){
                res = this.tail.value;
                this.tail = null;
                head.next = null;
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
        return 0;
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


}
