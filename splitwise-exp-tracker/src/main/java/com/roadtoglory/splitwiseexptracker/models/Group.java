package com.roadtoglory.splitwiseexptracker.models;

import com.roadtoglory.splitwiseexptracker.constants.Types;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


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
@Table(name = "group_details")
public class Group
{
    @Id
    //    @GeneratedValue(strategy = GenerationType.AUTO) - Will be used in case of the seq is created by us
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Types type;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @Column(name = "user_id_list")
    private String userIdList;

    @Column(name="created_by")
    private int createdById;

    @Column(name = "active_status")
    private boolean status;

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

    public void setId (int id)
    {
        this.id = id;
    }

    public Types getType ()
    {
        return type;
    }

    public void setType (Types type)
    {
        this.type = type;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getUserIdList ()
    {
        return userIdList;
    }

    public void setUserIdList (String userIdList)
    {
        this.userIdList = userIdList;
    }

    public int getCreatedById ()
    {
        return createdById;
    }

    public void setCreatedById (int createdById)
    {
        this.createdById = createdById;
    }

    public boolean isStatus ()
    {
        return status;
    }

    public void setStatus (boolean status)
    {
        this.status = status;
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
