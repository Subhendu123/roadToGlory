package com.roadtoglory.splitwiseexptracker.response;

import org.springframework.stereotype.Component;

import java.security.Timestamp;
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
//@Component
public class ExpenseErrorResponse
{
    private int status;
    private String message;
    private Long timestamp;

    public ExpenseErrorResponse (int status, String message)
    {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}
