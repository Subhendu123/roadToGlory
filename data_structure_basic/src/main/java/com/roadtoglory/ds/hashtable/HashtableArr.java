package com.roadtoglory.ds.hashtable;

import com.roadtoglory.data.Employee;
import com.roadtoglory.data.IndexedEmployee;


/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.ds.hashtable
   @Author Subhendu
   @Date 04-Jul-2024 07:22
*
*
*/
public class HashtableArr
{


    private static final int SIZE = 10;
    private IndexedEmployee[] hashtableArr;

    HashtableArr ()
    {
        hashtableArr = new IndexedEmployee[SIZE];
    }

    private int hash (String key)
    {
        return hashtableArr.length % key.length();
    }

    public IndexedEmployee get (String key)
    {
        int hashedVal = hash(key);

        while (hashedVal < hashtableArr.length)
        {
            IndexedEmployee retrievedEmp = hashtableArr[hashedVal];
            if (retrievedEmp != null && retrievedEmp.getStoredKey().equalsIgnoreCase(key))
            {
                return retrievedEmp;
            }
            hashedVal++;
        }
        return null;
    }

    public boolean remove (String key)
    {
        IndexedEmployee employee = get(key);
        if (employee == null)
        {
            System.out.println("The employee does not exist on our database. Please insert the employee first.");
            return false;
        }
        else
        {
            int hashedVal = hash(key);
            while (hashedVal < hashtableArr.length)
            {
                IndexedEmployee retrievedEmp = hashtableArr[hashedVal];
                if (retrievedEmp != null && retrievedEmp.getStoredKey().equalsIgnoreCase(key))
                {
                    System.out.println("The employee [" + retrievedEmp + "] is found at the position " + hashedVal);
                    hashtableArr[hashedVal] = null;
                    return true;
                }
                hashedVal++;
            }
            return false;
        }
    }

    private boolean occupied (int hashedKey)
    {
        return hashtableArr[hashedKey] != null;
    }

    public void put (String key, Employee value)
    {
        int hashedKey = hash(key);
        while (hashedKey < hashtableArr.length && occupied(hashedKey))
        {
            hashedKey++;
        }

        if (hashtableArr[hashedKey] != null)
        {
            System.out.println("An employee exists in the other locations.");
        }
        else
        {
            IndexedEmployee indEmp = new IndexedEmployee();
            indEmp.setEmployee(value);
            indEmp.setStoredKey(key);
            hashtableArr[hashedKey] = indEmp;
        }
    }

    public void printHashtable ()
    {
        for (IndexedEmployee element : this.hashtableArr)
        {
            if (element == null)
            {
                System.out.println("Empty value");
            }
            else
            {
                System.out.println(element);
            }
        }
    }


}
