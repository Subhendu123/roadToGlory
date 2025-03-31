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


    private final int status;
    private final String message;
    private final Long timestamp;

    public ExpenseResponse (int status, String message)
    {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }


}
