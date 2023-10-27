package com.roadtoglory.ds.linkedlists;

/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/
public class DoublyLinkedListImpl implements LinkedList
{
    private static Node head;
    Node tail;
    int length;

    public DoublyLinkedListImpl(int value){
        if(this.head == null){
            this.head = new Node(value);
            this.tail = this.head;
            this.length = 1;
        }

    }

    class Node {
        int value;
        Node next;
        Node prev;

        Node(int value){
            this.value = value;
        }

    }

    /**
     *
     */
    @Override
    public void printList()
    {
        Node itrNode = this.head;
        System.out.println("");
        while (itrNode != null){
            System.out.print(itrNode.value + " -> ");
            itrNode = itrNode.next;
        }

    }

    /**
     *
     */
    @Override
    public void getLength()
    {
        System.out.println("Length "+this.length);
    }

    /**
     * @param value
     */
    @Override
    public void append(int value)
    {
        Node itrNode = this.head;
        Node newEntry = new Node(value);
        while (itrNode != null){
            if(itrNode.next == null){
                itrNode.next = newEntry;
                newEntry.prev = itrNode;
                this.tail = newEntry;
                this.length++;
                break;
            }
            itrNode = itrNode.next;
        }
        printList();
    }

    /**
     * @param value
     */
    @Override
    public void prepend(int value)
    {

        Node itrNode = this.head;
        if(itrNode != null){
            Node newEntry = new Node(value);
            newEntry.next = itrNode;
            itrNode.prev = newEntry;
            this.head = newEntry;
            this.length++;
            printList();
        }

    }

    /**
     * @param value
     * @param index
     */
    @Override
    public void addWithIndex(int value, int index)
    {
        if(index <= this.length){
            Node itrNode = this.head;
            int counter = 1;
            while (itrNode != null){
                if(index == counter){
                    Node temp = new Node(value);
                    Node prev = itrNode.prev;
                    prev.next = temp;
                    temp.prev = prev;
                    temp.next = itrNode;
                    itrNode.prev = temp;
                    this.length++;
                    break;
                }
                itrNode = itrNode.next;
                counter++;
            }
        }
    }

    /**
     * @return
     */
    @Override
    public Node removeFirst()
    {
        Node removedNode = null;
        if(this.tail != null){
            removedNode = this.tail;
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.length--;
        }
        System.out.println("The removed node is "+removedNode.toString());
        return removedNode;
    }

    /**
     * @return
     */
    @Override
    public Node removeLast()
    {
        Node removedNode = null;
        if(this.tail != null){
            removedNode = this.tail;
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.length--;
        }
        System.out.println("The removed node is "+removedNode.toString());
        return removedNode;
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

    /**
     *
     */
    @Override
    public void reverse()
    {
        if(this.head == null || this.tail == null || this.length == 0)
            return;

        Node reverseNode = null;
        Node newHead = null;
        Node revItr = this.tail;
        while(revItr != null){
            if(reverseNode == null){
                reverseNode = revItr;
                Node prev = revItr.prev;
                reverseNode.prev = null;
                reverseNode.next = prev;
                prev.prev = reverseNode;
                if(newHead == null)
                    newHead = reverseNode;

            }else{
                reverseNode = revItr;
                Node prev = revItr.prev;
                reverseNode.next = prev;
                if(prev != null)
                    prev.prev = reverseNode;
            }

            reverseNode = reverseNode.next;
            revItr = revItr.prev;
        }
        this.head = newHead;
    }
}
