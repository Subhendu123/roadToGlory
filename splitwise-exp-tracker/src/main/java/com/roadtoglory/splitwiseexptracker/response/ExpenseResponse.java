package com.roadtoglory.splitwiseexptracker.response;

/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class ExpenseResponse {


    private int status;
    private String message;
    private Long timestamp;

    public ExpenseResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
