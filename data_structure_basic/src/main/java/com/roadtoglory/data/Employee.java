package com.roadtoglory.data;

import java.time.LocalDate;
import java.util.Random;


/*
*
*
   This is created by Subhendu (2024) for the project: data_structure_basic
        
   @Package name com.roadtoglory.data
   @Author Subhendu
   @Date 04-Jul-2024 07:31
*
*
*/
public class Employee
{


    private int emplId;
    private String name;
    private LocalDate dateJoined;

    public Employee (String name)
    {
        Random random = new Random();
        this.emplId = random.nextInt();
        this.name = name;
        this.dateJoined = LocalDate.now();

    }

    public int getEmplId ()
    {
        return emplId;
    }

    public String getName ()
    {
        return name;
    }

    public LocalDate getDateJoined ()
    {
        return dateJoined;
    }

    @Override
    public String toString ()
    {
        return "Employee{" +
                       "emplId=" + emplId +
                       ", name='" + name + '\'' +
                       ", dateJoined=" + dateJoined +
                       '}';
    }


}
