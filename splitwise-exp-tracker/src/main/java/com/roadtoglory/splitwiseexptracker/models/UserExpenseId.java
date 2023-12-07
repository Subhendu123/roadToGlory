package com.roadtoglory.splitwiseexptracker.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/
public class UserExpenseId implements Serializable
{
    //    @Column(name="user_id")
    private int userId;

    //    @Column(name="exp_id")
    private int expId;
    public int getUserId ()
    {
        return userId;
    }

    public void setUserId (int userId)
    {
        this.userId = userId;
    }

    public int getExpId ()
    {
        return expId;
    }

    public void setExpId (int expId)
    {
        this.expId = expId;
    }
    public UserExpenseId (){

    }

    @Override
    public boolean equals (Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserExpenseId that = (UserExpenseId) o;
        return userId == that.userId && expId == that.expId;
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(userId, expId);
    }

    public UserExpenseId (int userId, int expId)
    {
        this.userId = userId;
        this.expId = expId;
    }


}
