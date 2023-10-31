package testing;

import com.roadtoglory.ds.hashtable.Hashtable;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/

public class HTTesting
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
        hashtable.printTable();
    }
}
