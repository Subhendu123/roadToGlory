package com.roadtoglory.data;

/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.data
   @Author Subhendu
   @Date 19-Jul-2024 07:35
*
*
*/
public class IndexedEmployee
{


    private Employee employee;

    // this is the key used while storing the element
    private String storedKey;


    public Employee getEmployee ()
    {
        return employee;
    }

    public void setEmployee (Employee employee)
    {
        this.employee = employee;
    }

    public String getStoredKey ()
    {
        return storedKey;
    }

    public void setStoredKey (String storedKey)
    {
        this.storedKey = storedKey;
    }

    @Override
    public String toString ()
    {
        return "IndexedEmployee{" +
                       "employee=" + employee +
                       ", storedKey='" + storedKey + '\'' +
                       '}';
    }


}
