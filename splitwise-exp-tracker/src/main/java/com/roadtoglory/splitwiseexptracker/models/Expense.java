package com.roadtoglory.splitwiseexptracker.models;

import com.roadtoglory.splitwiseexptracker.constants.ExpCategories;
import com.roadtoglory.splitwiseexptracker.constants.SplitMethod;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;


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
@Table(name = "expense_details")
public class Expense
{
    @Id
    //    @GeneratedValue(strategy = GenerationType.AUTO) - Will be used in case of the seq is created by us
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ExpCategories category;

    @Column(name = "amount")
    private Double totalAmount;

    @Column(name = "description")
    private String description;


//    @Column(name = "user_id_list")
    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserExpense> userExpenses;


    @Column(name="paid_by")
    private int paidBy;

    @Column(name = "split_method")
    private SplitMethod splitMethod;

    @Column(name="group_id")
    private int groupId;

    @Column(name = "active_status")
    private boolean activeStatus;
    @Column(name = "prm_curr_id", length = 3)
    private String currencyCode;
    @Column(name="created_by")
    private int createdById;
    @Column(name="updated_by")
    private int updatedById;
    @Column(name = "created_on", updatable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "updated_on")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    public int getId ()
    {
        return id;
    }

//    public void setId (int id)
//    {
//        this.id = id;
//    }

    public SplitMethod getSplitMethod ()
    {
        return splitMethod;
    }

    public void setSplitMethod (SplitMethod splitMethod)
    {
        this.splitMethod = splitMethod;
    }

    public ExpCategories getCategory ()
    {
        return category;
    }

    public void setCategory (ExpCategories category)
    {
        this.category = category;
    }

    public Double getTotalAmount ()
    {
        return totalAmount;
    }

    public void setTotalAmount (Double totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public List<UserExpense> getUserExpenses ()
    {
        return userExpenses;
    }

    public void setUserExpenses (List<UserExpense> userExpenses)
    {
        this.userExpenses = userExpenses;
    }

    public int getPaidBy ()
    {
        return paidBy;
    }

    public void setPaidBy (int paidBy)
    {
        this.paidBy = paidBy;
    }

    public int getGroupId ()
    {
        return groupId;
    }

    public void setGroupId (int groupId)
    {
        this.groupId = groupId;
    }

    public boolean isActiveStatus ()
    {
        return activeStatus;
    }

    public void setActiveStatus (boolean activeStatus)
    {
        this.activeStatus = activeStatus;
    }

    public String getCurrencyCode ()
    {
        return currencyCode;
    }

    public void setCurrencyCode (String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public int getCreatedById ()
    {
        return createdById;
    }

    public void setCreatedById (int createdById)
    {
        this.createdById = createdById;
    }

    public int getUpdatedById ()
    {
        return updatedById;
    }

    public void setUpdatedById (int updatedById)
    {
        this.updatedById = updatedById;
    }

    public LocalDateTime getCreateDate ()
    {
        return createDate;
    }

    public void setCreateDate (LocalDateTime createDate)
    {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate ()
    {
        return updateDate;
    }

    public void setUpdateDate (LocalDateTime updateDate)
    {
        this.updateDate = updateDate;
    }
}
