package com.road2glory.splitwiseexpensetrackingapp.models;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessage {

    private String message;
    private int status_code;

    public ResponseMessage(){

    }

    public ResponseMessage(String message, int status_code) {
        this.message = message;
        this.status_code = status_code;
    }
}
