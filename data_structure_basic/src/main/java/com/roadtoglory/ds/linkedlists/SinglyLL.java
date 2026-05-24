/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.linkedlists;
/*
*
*

This class SinglyLL is created and managed by subhe
Created on 24-05-2026 at 00:32 for the project Linked List

*
*
*/

public class SinglyLL implements LinkedList {

    private int length = 0;
    private Node head;


    public SinglyLL(int data) {
        if (this.head == null) {
            this.head = new Node(data);
        }
    }


    /**
     *
     */
    @Override
    public void printList() {
        System.out.println("--------------------------------------");
        print(this.head);
        System.out.println("--------------------------------------");
    }

    private void print(Node head) {
        if (head == null) {
            return;
        }
        if (head.next != null) {
            System.out.print(head.data + " -> ");
        }
        else {
            System.out.print(head.data);
        }
        print(head.next);

    }


    /**
     *
     */
    @Override
    public void getLength() {
        Node headTr = this.head;
        length = 0;
        while (headTr != null) {
            length++;
            headTr = headTr.next;
        }
        System.out.println("The length is " + length);
    }

    /**
     * @param value
     */
    @Override
    public void append(int value) {
        add(value, this.head);

    }

    private Node add(int value, Node head) {
        if (head == null) {
            return new Node(value);
        }
        head.next = add(value, head.next);
        return head;
    }

    /**
     * @param value
     */
    @Override
    public void prepend(int value) {
        Node node = new Node(value);
        node.next = head;
        this.head = node;

    }

    /**
     * @param value
     * @param index
     */
    @Override
    public void insert(int value, int index) {

        getLength();
        if (this.length <= index) {
            throw new IllegalArgumentException("Invalid Index.");
        }

        Node tr = this.head;
        int currIndex = 0;
        while (currIndex < index) {
            if (currIndex == index - 1) {
                Node newNode = new Node(value);
                Node nextToNode = tr.next;
                tr.next = newNode;
                newNode.next = nextToNode;
                break;
            }
            tr = tr.next;
            currIndex++;
        }


    }

    /**
     * @return
     */
    @Override
    public Node removeFirst() {
        this.head = this.head.next;
        return this.head;
    }

    /**
     * @return
     */
    @Override
    public Object removeLast() {
        return null;
    }

    /**
     * @param index
     * @return
     */
    @Override
    public int removeIndexWise(int index) {
        getLength();
        if (this.length <= index) {
            throw new IllegalArgumentException("Invalid Index.");
        }

        Node tr = this.head;
        int currIndex = 0;
        while (currIndex < index) {
            if (currIndex == index - 1) {
                Node nextToNode = tr.next;
                tr.next = nextToNode.next;
                return nextToNode.data;
            }
            tr = tr.next;
            currIndex++;
        }
        return -1;
    }

    /**
     * @param index
     * @param value
     */
    @Override
    public void replaceIndex(int index, int value) {

    }

    /**
     * @param index1
     * @param index2
     */
    @Override
    public void substitute(int index1, int index2) {

    }

    /**
     *
     */
    @Override
    public void reverse() {

    }

    /**
     *
     */
    @Override
    public void sort() {

    }

    /**
     * @param value
     */
    @Override
    public void insertIntoSortedList(int value) {

    }

    public class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }
}
