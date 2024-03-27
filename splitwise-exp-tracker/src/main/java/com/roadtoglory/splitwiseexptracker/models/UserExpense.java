package com.roadtoglory.splitwiseexptracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/
@Entity
//@IdClass(UserExpenseId.class)
@Table(name = "user_expense_details")
public class UserExpense
{


    @Id
    //    @GeneratedValue(strategy = GenerationType.AUTO) - Will be used in case of the seq is created by us
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    //    @Id
    //    @Column(name="exp_id", nullable = false)
    //    private int expId;

    //    @EmbeddedId
    //    private UserExpenseId userExpenseId;

    @ManyToOne
    @JoinColumn(name = "exp_id")
    @JsonIgnore
    private Expense expense;

    @Column(name = "active_status")
    private boolean activeStatus;

    @Column(name = "user_share")
    private Double userShare;

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public Expense getExpense ()
    {
        return expense;
    }

    public void setExpense (Expense expense)
    {
        this.expense = expense;
    }

    public int getUserId ()
    {
        return userId;
    }

    public void setUserId (int userId)
    {
        this.userId = userId;
    }

    //    public int getExpId ()
    //    {
    //        return expId;
    //    }
    //
    //    public void setExpId (int expId)
    //    {
    //        this.expId = expId;
    //    }

    public boolean isActiveStatus ()
    {
        return activeStatus;
    }

    public void setActiveStatus (boolean activeStatus)
    {
        this.activeStatus = activeStatus;
    }

    public Double getUserShare ()
    {
        return userShare;
    }

    public void setUserShare (Double userShare)
    {
        this.userShare = userShare;
    }


}
