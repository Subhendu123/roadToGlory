package com.roadtoglory.ds.linkedlists;

/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public interface LinkedList
{
    public void printList();
    public void getLength();
    public void append(int value);
    public void prepend(int value);

    public void addWithIndex(int value, int index);

    public Node removeFirst();
    public Node removeLast();
    public int removeIndexWise(int index);
    public void replaceIndex(int index, int value);
    public void substitute(int index1, int index2);
    public void reverse();


}
