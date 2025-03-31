package com.roadtoglory.splitwiseexptracker.exceptions;

import com.roadtoglory.splitwiseexptracker.response.ExpenseResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/
@ControllerAdvice
public class GlobalExceptionHandler
{


    private static final Logger LOG = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BadSplitInformationException.class)
    public ResponseEntity<ExpenseResponse> handleExceptionForBadSplitInfo (BadSplitInformationException exc)
    {
        LOG.error("SplitwiseExpTrackerApplication - Some Abnormality is being encountered with!");
        String msg = exc.getMessage();
        ExpenseResponse expenseErrorResponse = new ExpenseResponse(HttpStatus.BAD_REQUEST.value(), msg);
        return new ResponseEntity<>(expenseErrorResponse, HttpStatus.BAD_REQUEST);
    }

    //    @ExceptionHandler(Exception.class)
    //    public ResponseEntity<String> handleOtherExceptions (Exception ex)
    //    {
    //        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    //    }


}
