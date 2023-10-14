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

        @Override
        public String toString()
        {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    ", prev=" + prev +
                    '}';
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
        Node itrNode = this.head;
    }

    /**
     * @return
     */
    @Override
    public Node removeFirst()
    {
        return null;
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

    }
}
