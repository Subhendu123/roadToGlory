package com.roadtoglory.ds.hashtable;

/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class HashTableTesting
{
    public static void main (String[] args)
    {
        Hashtable hashtable = new Hashtable();
        hashtable.insert("A", 10);
        hashtable.insert("B", 20);
        hashtable.insert("C", 12);
        hashtable.insert("D", 14);
        hashtable.insert("E", 15);
        hashtable.insert("F", 34);
        hashtable.insert("F", 51);
        hashtable.insert("G", 50);
        hashtable.printTable();
        Hashtable.Node node = hashtable.search("A", 10);
        System.out.println("node " + node.key + " : " + node.value);
    }
}
