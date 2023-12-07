package com.roadtoglory.splitwiseexptracker.dto;

import org.springframework.stereotype.Component;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/
@Component
public class SplitInfoDto
{
    private Integer userId;

    private int share;
    private double customAmount;

    private double percentage;

    public Integer getUserId ()
    {
        return userId;
    }

    public void setUserId (Integer userId)
    {
        this.userId = userId;
    }

    public int getShare ()
    {
        return share;
    }

    public void setShare (int share)
    {
        this.share = share;
    }

    public double getCustomAmount ()
    {
        return customAmount;
    }

    public void setCustomAmount (double customAmount)
    {
        this.customAmount = customAmount;
    }

    public double getPercentage ()
    {
        return percentage;
    }

    public void setPercentage (double percentage)
    {
        this.percentage = percentage;
    }
}
