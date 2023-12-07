package com.roadtoglory.splitwiseexptracker.controller;

import com.roadtoglory.splitwiseexptracker.dto.ExpenseDetailsDto;
import com.roadtoglory.splitwiseexptracker.dto.ExpenseResponseDto;
import com.roadtoglory.splitwiseexptracker.exceptions.BadSplitInformationException;
import com.roadtoglory.splitwiseexptracker.exceptions.IncompleteRequestException;
import com.roadtoglory.splitwiseexptracker.models.Expense;
import com.roadtoglory.splitwiseexptracker.response.ExpenseResponse;
import com.roadtoglory.splitwiseexptracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/
@RestController
@RequestMapping("/testingexp")
public class ExpenseTrackController
{


    @Autowired
    private ExpenseService expenseService;

    @ExceptionHandler(value = BadSplitInformationException.class)
    public ResponseEntity<ExpenseResponse> handleException (BadSplitInformationException exc)
    {
        ExpenseResponse expenseErrorResponse = new ExpenseResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());

        return new ResponseEntity<>(expenseErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IncompleteRequestException.class)
    public ResponseEntity<ExpenseResponse> handleIncompleteRequestException (IncompleteRequestException exc)
    {
        ExpenseResponse expenseErrorResponse = new ExpenseResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());

        return new ResponseEntity<>(expenseErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addExpense (@RequestBody ExpenseDetailsDto expenseDetailsDto)
    {
        expenseService.addExpense(expenseDetailsDto);
        String message = "Expense Added Successfully";
        //        ExpenseResponse expenseResponse = new ExpenseResponse(HttpStatus.CREATED.value(),
        //                message);
        return message;
    }

    @GetMapping(value = "/getExpenses")
    public List<Expense> getExpensesForGroup (@RequestParam("group_id") int groupId)
    {
        return expenseService.findAllExpensesForGroup(groupId);
    }

    @GetMapping(value = "/getExpenses/individual")
    public ExpenseResponseDto getExpensesForGroup (@RequestParam("group_id") int groupId, @RequestParam("user_id") int userId)
    {
        return expenseService.findIndExpDetailsForUserInGroup(userId, groupId);
    }


}
