package com.roadtoglory.splitwiseexptracker.response;

/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class ExpenseResponse
{
    private int status;
    private String message;
    private Long timestamp;

    public ExpenseResponse (int status, String message)
    {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}
