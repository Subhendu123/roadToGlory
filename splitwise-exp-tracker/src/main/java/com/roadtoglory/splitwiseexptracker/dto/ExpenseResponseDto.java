package com.roadtoglory.splitwiseexptracker.dto;

/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class ExpenseResponseDto
{


    private int userId;
    private int groupId;
    private double totalExpAmount;
    private double totalIndividualShare;
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

    public double getTotalExpAmount ()
    {
        return totalExpAmount;
    }

    public void setTotalExpAmount (double totalExpAmount)
    {
        this.totalExpAmount = totalExpAmount;
    }

    public double getTotalIndividualShare ()
    {
        return totalIndividualShare;
    }

    public void setTotalIndividualShare (double totalIndividualShare)
    {
        this.totalIndividualShare = totalIndividualShare;
    }

    public boolean isOweStatus ()
    {
        return oweStatus;
    }

    public void setOweStatus (boolean oweStatus)
    {
        this.oweStatus = oweStatus;
    }


}
