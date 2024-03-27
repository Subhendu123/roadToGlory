package com.roadtoglory.splitwiseexptracker.dto;

/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class SimpleExpenseResponse
{


    private int userId;
    private int groupId;
    private double totalGrpExpense;
    private double indvShareAmount;
    private double totalIndExpense;

    // owestatus - true means the user would get back the amount from the group
    // owestatus - false means the user has to pay the amount to the group
    private boolean oweStatus;



    public int getUserId ()
    {
        return userId;
    }

    public void setUserId (int userId)
    {
        this.userId = userId;
    }

    public int getGroupId ()
    {
        return groupId;
    }

    public void setGroupId (int groupId)
    {
        this.groupId = groupId;
    }

    public double getTotalGrpExpense ()
    {
        return totalGrpExpense;
    }

    public void setTotalGrpExpense (double totalGrpExpense)
    {
        this.totalGrpExpense = totalGrpExpense;
    }

    public double getIndvShareAmount ()
    {
        return indvShareAmount;
    }

    public void setIndvShareAmount (double indvShareAmount)
    {
        this.indvShareAmount = indvShareAmount;
    }

    public boolean isOweStatus ()
    {
        return oweStatus;
    }

    public void setOweStatus (boolean oweStatus)
    {
        this.oweStatus = oweStatus;
    }

    public double getTotalIndExpense ()
    {
        return totalIndExpense;
    }

    public void setTotalIndExpense (double totalIndExpense)
    {
        this.totalIndExpense = totalIndExpense;
    }


}
