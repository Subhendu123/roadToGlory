package com.roadtoglory.splitwiseexptracker.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.Temporal;

import java.security.Timestamp;
import java.sql.Date;
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
@Table(name = "user_details")
public class User
{
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) - Will be used in case of the seq is created by us
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "active_status")
    private boolean status;
    @Column(name = "prm_curr_id", length = 3)
    private String currencyCode;

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

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getPhoneNumber ()
    {
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public boolean isStatus ()
    {
        return status;
    }

    public void setStatus (boolean status)
    {
        this.status = status;
    }

    public String getCurrencyCode ()
    {
        return currencyCode;
    }

    public void setCurrencyCode (String currencyCode)
    {
        this.currencyCode = currencyCode;
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

    @Override
    public String toString ()
    {
        return "User{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", status=" + status + ", currencyCode='" + currencyCode + '\'' + ", createDate=" + createDate + ", updateDate=" + updateDate + '}';
    }
}
