package com.roadtoglory.splitwiseexptracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadSplitInformationException extends RuntimeException
{


    public BadSplitInformationException (String message)
    {
        super(message);
    }


}
