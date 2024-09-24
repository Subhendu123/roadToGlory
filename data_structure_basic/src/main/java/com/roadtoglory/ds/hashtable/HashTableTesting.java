package com.roadtoglory.ds.hashtable;

import com.roadtoglory.data.Employee;


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

        HashtableArr hashtableArr = new HashtableArr();
        hashtableArr.put("Subhendu", new Employee("Subhendu"));
        hashtableArr.put("Ghoton", new Employee("Subhadip"));
        hashtableArr.put("Dandi", new Employee("Suman"));
        hashtableArr.put("Krishanu", new Employee("Krishanu"));
        hashtableArr.put("Dandiiii", new Employee("Dandiiii"));
        hashtableArr.printHashtable();
        System.out.println("the retrieved data (D): " + hashtableArr.get("Dandiiii"));
        System.out.println("the retrieved data (K): " + hashtableArr.get("Krishanu"));
        System.out.println("the retrieved data (S):" + hashtableArr.get("Subhendu"));
        System.out.println("Remove (K) " + hashtableArr.remove("Krishanu"));
        hashtableArr.printHashtable();
        System.out.println("Remove (Ghoton) " + hashtableArr.remove("Ghoton"));
        System.out.println("Remove (Dandi) " + hashtableArr.remove("Dandi"));
        System.out.println("Remove (Subhendu) " + hashtableArr.remove("Subhendu"));
        System.out.println("Remove (Dandiiii) " + hashtableArr.remove("Dandiiii"));
        System.out.println("\n the print hashtable val: ");
        hashtableArr.printHashtable();


      /*  Hashtable hashtable = new Hashtable();
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
        System.out.println("node " + node.key + " : " + node.value);*/
    }


}
