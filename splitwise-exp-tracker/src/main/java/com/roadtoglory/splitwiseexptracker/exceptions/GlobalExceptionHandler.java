package com.roadtoglory.splitwiseexptracker.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


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
public class GlobalExceptionHandler {


    private static final Logger LOG = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BadSplitInformationException.class)
    public ResponseEntity<Map<String, String>> handleExceptionForBadSplitInfo(
            BadSplitInformationException exc
    ) {
        LOG.error("SplitwiseExpTrackerApplication - Some Abnormality is being encountered with!", exc);
        String msg = exc.getMessage();
//        ExpenseResponse expenseErrorResponse = new ExpenseResponse(HttpStatus.BAD_REQUEST.value(), msg);
        Map<String, String> finalResp = new HashMap<>();
        finalResp.put("ERROR", msg);
        finalResp.put("STATUS_CODE", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        finalResp.put("STATUS", HttpStatus.BAD_REQUEST.getReasonPhrase());

        return new ResponseEntity<>(finalResp, HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(expenseErrorResponse, HttpStatus.BAD_REQUEST);
    }

    //    @ExceptionHandler(Exception.class)
    //    public ResponseEntity<String> handleOtherExceptions (Exception ex)
    //    {
    //        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    //    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleBadJson(HttpMessageNotReadableException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Bad Request - Invalid JSON or Type Mismatch");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Validation failed");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAll(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Something went wrong");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
