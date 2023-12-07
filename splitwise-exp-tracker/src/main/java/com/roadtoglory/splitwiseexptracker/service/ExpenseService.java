package com.roadtoglory.splitwiseexptracker.service;

import com.roadtoglory.splitwiseexptracker.constants.SplitMethod;
import com.roadtoglory.splitwiseexptracker.dao.ExpenseJPARepo;
import com.roadtoglory.splitwiseexptracker.dao.GroupJPARepo;
import com.roadtoglory.splitwiseexptracker.dao.SplitJPARepo;
import com.roadtoglory.splitwiseexptracker.dao.UserJPARepo;
import com.roadtoglory.splitwiseexptracker.dto.ExpenseDetailsDto;
import com.roadtoglory.splitwiseexptracker.dto.ExpenseResponseDto;
import com.roadtoglory.splitwiseexptracker.dto.SplitInfoDto;
import com.roadtoglory.splitwiseexptracker.helper.ExpenseServiceHelper;
import com.roadtoglory.splitwiseexptracker.models.Expense;
import com.roadtoglory.splitwiseexptracker.models.Group;
import com.roadtoglory.splitwiseexptracker.models.User;
import com.roadtoglory.splitwiseexptracker.models.UserExpense;
import com.roadtoglory.splitwiseexptracker.validators.ExpenseServiceValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
@Transactional
public class ExpenseService
{


    @Autowired
    private ExpenseJPARepo expenseJPARepo;

    @Autowired
    private GroupJPARepo groupJPARepo;

    @Autowired
    private UserJPARepo userJPARepo;
    @Autowired
    private SplitJPARepo splitJPARepo;

    @Transactional
    public void addExpense (ExpenseDetailsDto expenseDetailsDto)
    {

        // validate the amount params
        ExpenseServiceValidator.validateExpenseDto(expenseDetailsDto);

        // validate the group
        // if the group is present
        Group groupDetails = groupJPARepo.findById(expenseDetailsDto.getExpenseDetails().getGroupId()).orElse(null);
        ExpenseServiceValidator.validateOrRaiseExc(groupDetails == null, "Incorrect Group" + " Information! Please provide the correct details group!");

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

        List<Expense> expenses = expenseJPARepo.findAll();
        return expenses.stream().filter(expense -> {
            System.out.println("The expense group id is " + expense.getGroupId());
            return expense.getGroupId() == groupId;
        }).toList();

    }

    public ExpenseResponseDto findIndExpDetailsForUserInGroup (int userId, int groupId)
    {

        User userDetails = userJPARepo.findById(userId).orElse(null);
        ExpenseServiceValidator.validateOrRaiseExc(userDetails == null, "User does not exist or is deleted!");

        //        Group groupDetails = groupJPARepo.findById(groupId).orElse(null);
        //        ExpenseServiceValidator.validateOrRaiseExc(groupDetails == null, "The Group Entered does not exist or has been deleted!");

        List<Expense> expeListInGroup = findAllExpensesForGroup(groupId);

        List<UserExpense> users = expeListInGroup.stream().flatMap(s -> s.getUserExpenses().stream().filter(userExpense -> userExpense.getUserId() == userId)).toList();

        //        List<Expense> expenseList = expenseJPARepo.findAll().stream().filter(expense -> expense.getGroupId() == groupId).toList();
        //
        //        List<UserExpense> users = expenseList.stream().flatMap(s -> s.getUserExpenses().stream().filter(userExpense -> userExpense.getUserId() == userId)).toList();

        //        List<UserExpense> users = expenseJPARepo.findAll().stream().filter(expense -> expense.getGroupId() == groupId).flatMap(s -> s.getUserExpenses().stream().filter(userExpense -> userExpense.getUserId() == userId)).toList();

        double totalUserShare = users.stream().mapToDouble(UserExpense::getUserShare).sum();
        double totalExpense = expeListInGroup.stream().mapToDouble(Expense::getTotalAmount).sum();

        ExpenseResponseDto responseDto = new ExpenseResponseDto();
        responseDto.setGroupId(groupId);
        responseDto.setUserId(userId);
        responseDto.setTotalExpAmount(totalExpense);
        responseDto.setTotalIndividualShare(totalUserShare);

        return responseDto;
    }

    private void saveSplitDetails (ExpenseDetailsDto expenseDetailsDto, Expense expense, double indvShare)
    {
        for (SplitInfoDto splitInfoDto : expenseDetailsDto.getSplitInfoDtos())
        {
            UserExpense userExpense = ExpenseServiceHelper.getUserExpense(expenseDetailsDto, expense, indvShare, splitInfoDto);
            splitJPARepo.saveAndFlush(userExpense);
        }
    }


}
