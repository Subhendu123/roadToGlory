package com.roadtoglory.splitwiseexptracker.service;

import com.roadtoglory.splitwiseexptracker.constants.SplitMethod;
import com.roadtoglory.splitwiseexptracker.controller.ExpenseTrackController;
import com.roadtoglory.splitwiseexptracker.dao.ExpenseJPARepo;
import com.roadtoglory.splitwiseexptracker.dao.GroupJPARepo;
import com.roadtoglory.splitwiseexptracker.dao.SplitJPARepo;
import com.roadtoglory.splitwiseexptracker.dao.UserJPARepo;
import com.roadtoglory.splitwiseexptracker.dto.ExpenseDetailsDto;
import com.roadtoglory.splitwiseexptracker.dto.ExtendedExpenseResponse;
import com.roadtoglory.splitwiseexptracker.dto.SimpleExpenseResponse;
import com.roadtoglory.splitwiseexptracker.dto.SplitInfoDto;
import com.roadtoglory.splitwiseexptracker.helper.ExpenseServiceHelper;
import com.roadtoglory.splitwiseexptracker.models.Expense;
import com.roadtoglory.splitwiseexptracker.models.Group;
import com.roadtoglory.splitwiseexptracker.models.User;
import com.roadtoglory.splitwiseexptracker.models.UserExpense;
import com.roadtoglory.splitwiseexptracker.validators.ExpenseServiceValidator;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.roadtoglory.splitwiseexptracker.helper.ExpenseServiceHelper.evaluateExpenses;
import static com.roadtoglory.splitwiseexptracker.helper.ExpenseServiceHelper.formatExpansesWithDto;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/
@Service
@Transactional
public class ExpenseService
{


    private static final Logger LOG = LogManager.getLogger(ExpenseTrackController.class);



    @Autowired
    private ExpenseJPARepo expenseJPARepo;

    @Autowired
    private GroupJPARepo groupJPARepo;

    @Autowired
    private UserJPARepo userJPARepo;
    @Autowired
    private SplitJPARepo splitJPARepo;
    private List<Expense> expenses;

    @Transactional
    public void addExpense (ExpenseDetailsDto expenseDetailsDto)
    {

        LOG.info("SplitwiseExpTrackerApplication - The add expense service method is getting executed");
        LOG.debug("SplitwiseExpTrackerApplication - Validating the expense details");
        // validate the amount params
        ExpenseServiceValidator.validateExpenseDto(expenseDetailsDto);

        LOG.debug("SplitwiseExpTrackerApplication - Validation of the expense details input is completed!");

        LOG.debug("SplitwiseExpTrackerApplication - validate the group if the group exists");

        // validate the group
        // if the group is present
        Group groupDetails = groupJPARepo.findById(expenseDetailsDto.getExpenseDetails().getGroupId()).orElse(null);
        LOG.debug("SplitwiseExpTrackerApplication - The Group Details are " + groupDetails);

        ExpenseServiceValidator.validateOrRaiseExc(groupDetails == null, "Incorrect Group Information! Please provide the correct details group!");

        LOG.debug("SplitwiseExpTrackerApplication - validateOrRaiseExc is done");

        LOG.info("SplitwiseExpTrackerApplication - Saving the expense details " + expenseDetailsDto.getExpenseDetails());

        // if the users belong to the group or not - to be worked on
        Expense expense = expenseJPARepo.saveAndFlush(expenseDetailsDto.getExpenseDetails());
        //            Integer expenseId = expense.getId();

        if (expenseDetailsDto.getExpenseDetails().getSplitMethod().equals(SplitMethod.EQUAL))
        {
            int numberOfParties = expenseDetailsDto.getSplitInfoDtos().size();
            double indvShare = expenseDetailsDto.getExpenseDetails().getTotalAmount() / numberOfParties;
            // save using dto for each of the individual
            saveSplitDetails(expenseDetailsDto, expense, indvShare);
        }
        else
        {
            saveSplitDetails(expenseDetailsDto, expense, -1);
        }


    }

    public List<Expense> findAllExpensesForGroup (int groupId)
    {
        Group groupDetails = groupJPARepo.findById(groupId).orElse(null);
        ExpenseServiceValidator.validateOrRaiseExc(groupDetails == null, "The Group Entered does not exist or has been deleted!");

        List<Expense> expenseResponseList = new ArrayList<>();

        List<Expense> expenses = expenseJPARepo.findAll();
        return formatExpansesWithDto(groupId, expenseResponseList, expenses);

    }

    public List<Integer> getUsersUnderGroup (int groupId)
    {
        Group groupDetails = groupJPARepo.findById(groupId).orElse(null);
        return null;
    }

    public SimpleExpenseResponse findIndExpDetailsForUserInGroup (int userId, int groupId)
    {

        User userDetails = userJPARepo.findById(userId).orElse(null);
        ExpenseServiceValidator.validateOrRaiseExc(userDetails == null, "User does not exist or is deleted!");

        //        Group groupDetails = groupJPARepo.findById(groupId).orElse(null);
        //        ExpenseServiceValidator.validateOrRaiseExc(groupDetails == null, "The Group Entered does not exist or has been deleted!");

        List<Expense> expeListInGroup = findAllExpensesForGroup(groupId);

        // payment not done by ind is -


        return ExpenseServiceHelper.getExpenseResponseDto(userId, groupId, expeListInGroup);
    }

    private void saveSplitDetails (ExpenseDetailsDto expenseDetailsDto, Expense expense, double indvShare)
    {
        for (SplitInfoDto splitInfoDto : expenseDetailsDto.getSplitInfoDtos())
        {
            UserExpense userExpense = ExpenseServiceHelper.getUserExpense(expenseDetailsDto, expense, indvShare, splitInfoDto);
            splitJPARepo.saveAndFlush(userExpense);
        }
    }

    public List<ExtendedExpenseResponse> evaluateSplitDetails (int groupId)
    {
        // fetch the users (user id) from the group table using the group id
        LOG.info("SplitwiseExpTrackerApplication - Evaluation of the split details is in progress...");
        List<Expense> txnsUnderGrp = findAllExpensesForGroup(groupId);
        if (txnsUnderGrp != null && LOG.isDebugEnabled())
        {
            LOG.debug("SplitwiseExpTrackerApplication - All the expenses under the group are retrieved. And the total number of transactions are " + txnsUnderGrp.size());
        }

        // to find out the users for group

        List<Integer> users = txnsUnderGrp.get(0)
                                          .getUserExpenses()
                                          .stream()
                                          .map(UserExpense::getUserId)
                                          .toList();
        if (users != null && LOG.isDebugEnabled())
        {
            LOG.debug("SplitwiseExpTrackerApplication - All the Users under the group are retrieved. And the total number of users under the group is " + users.size());
        }

        List<ExtendedExpenseResponse> expenseList = users.stream()
                                                         .map(userId -> ExpenseServiceHelper.getExpenseResponseDto(userId, groupId, txnsUnderGrp))
                                                         .toList();

        return evaluateExpenses(expenseList);
    }


}
