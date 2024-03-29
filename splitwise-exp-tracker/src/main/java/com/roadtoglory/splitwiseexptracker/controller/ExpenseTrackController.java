package com.roadtoglory.splitwiseexptracker.controller;

import com.roadtoglory.splitwiseexptracker.dto.ExpenseDetailsDto;
import com.roadtoglory.splitwiseexptracker.dto.ExtendedExpenseResponse;
import com.roadtoglory.splitwiseexptracker.dto.SimpleExpenseResponse;
import com.roadtoglory.splitwiseexptracker.exceptions.BadSplitInformationException;
import com.roadtoglory.splitwiseexptracker.exceptions.IncompleteRequestException;
import com.roadtoglory.splitwiseexptracker.models.Expense;
import com.roadtoglory.splitwiseexptracker.response.ExpenseResponse;
import com.roadtoglory.splitwiseexptracker.service.ExpenseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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


    private static final Logger LOG = LogManager.getLogger(ExpenseTrackController.class);

    @Autowired
    private ExpenseService expenseService;

    @ExceptionHandler(value = BadSplitInformationException.class)
    public ResponseEntity<ExpenseResponse> handleException (BadSplitInformationException exc)
    {
        LOG.error("SplitwiseExpTrackerApplication - Some Abnormality is being encountered with!");
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
        LOG.debug("SplitwiseExpTrackerApplication - Adding an expense..");
        expenseService.addExpense(expenseDetailsDto);
        LOG.info("SplitwiseExpTrackerApplication - Expense Added Successfully");
        String message = "Expense Added Successfully";
        //        ExpenseResponse expenseResponse = new ExpenseResponse(HttpStatus.CREATED.value(),
        //                message);
        return message;
    }

    @GetMapping(value = "/getExpenses")
    public List<Expense> getExpensesForGroup (@RequestParam("group_id") int groupId)
    {
        LOG.debug("SplitwiseExpTrackerApplication - Fetching Expenses for a group..");

        return expenseService.findAllExpensesForGroup(groupId);
    }

    @GetMapping(value = "/getExpenses/individual")
    public SimpleExpenseResponse getExpensesForGroup (@RequestParam("group_id") int groupId, @RequestParam("user_id") int userId)
    {
        LOG.debug("SplitwiseExpTrackerApplication - Fetching Expenses for an individual with id " + userId + " under " + "a group having id " + groupId);

        return expenseService.findIndExpDetailsForUserInGroup(userId, groupId);
    }

    @GetMapping(value = "/getExpenses/evaluated")
    public List<ExtendedExpenseResponse> getEvaluatedExpenses (@RequestParam("group_id") int groupId)
    {
        LOG.debug("SplitwiseExpTrackerApplication - Fetching Expenses for all members under a group having id " + groupId);

        return expenseService.evaluateSplitDetails(groupId);
    }


}
