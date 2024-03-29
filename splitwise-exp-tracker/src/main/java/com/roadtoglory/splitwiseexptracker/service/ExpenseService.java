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
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.roadtoglory.splitwiseexptracker.helper.ExpenseServiceHelper.populatePayorAndPayeeDetails;


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
        expenses.stream().filter(expense -> {
            LOG.debug("The expense group id is " + expense.getGroupId());
            //            System.out.println("The expense share " + expense.get);
            return expense.getGroupId() == groupId;
        }).forEach(expense -> {
            Expense expSetObject = new Expense();
            //            expSetObject.setUserExpenses(null);
            expSetObject.setCategory(expense.getCategory());
            expSetObject.setGroupId(expense.getGroupId());
            expSetObject.setUserExpenses(expense.getUserExpenses());
            expSetObject.setActiveStatus(expense.isActiveStatus());
            expSetObject.setDescription(expense.getDescription());
            expSetObject.setCreateDate(expense.getCreateDate());
            expSetObject.setCurrencyCode(expense.getCurrencyCode());
            expSetObject.setPaidBy(expense.getPaidBy());
            expSetObject.setCreatedById(expense.getCreatedById());
            expSetObject.setSplitMethod(expense.getSplitMethod());
            expSetObject.setTotalAmount(expense.getTotalAmount());
            expSetObject.setUpdateDate(expense.getUpdateDate());
            expSetObject.setUpdatedById(expense.getUpdatedById());
            expenseResponseList.add(expSetObject);
        });
        return expenseResponseList;

    }

    public SimpleExpenseResponse findIndExpDetailsForUserInGroup (int userId, int groupId)
    {

        User userDetails = userJPARepo.findById(userId).orElse(null);
        ExpenseServiceValidator.validateOrRaiseExc(userDetails == null, "User does not exist or is deleted!");

        //        Group groupDetails = groupJPARepo.findById(groupId).orElse(null);
        //        ExpenseServiceValidator.validateOrRaiseExc(groupDetails == null, "The Group Entered does not exist or has been deleted!");

        List<Expense> expeListInGroup = findAllExpensesForGroup(groupId);


        //        List<Expense> expenseList = expenseJPARepo.findAll().stream().filter(expense -> expense.getGroupId() == groupId).toList();
        //
        //        List<UserExpense> users = expenseList.stream().flatMap(s -> s.getUserExpenses().stream().filter(userExpense -> userExpense.getUserId() == userId)).toList();

        //        List<UserExpense> users = expenseJPARepo.findAll().stream().filter(expense -> expense.getGroupId() == groupId).flatMap(s -> s.getUserExpenses().stream().filter(userExpense -> userExpense.getUserId() == userId)).toList();

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
        List<Expense> expeListInGroup = findAllExpensesForGroup(groupId);
        List<Integer> users = expeListInGroup.get(0)
                                             .getUserExpenses()
                                             .stream()
                                             .map(UserExpense::getUserId)
                                             .toList();
        //        for (Integer userId : users)
        //        {
        //            ExtendedExpenseResponse expenseResponse = ExpenseServiceHelper.getExpenseResponseDto(userId, groupId, expeListInGroup);
        //            expenseList.add(expenseResponse);
        //        }

        List<ExtendedExpenseResponse> expenseList = users.stream()
                                                         .map(userId -> ExpenseServiceHelper.getExpenseResponseDto(userId, groupId, expeListInGroup))
                                                         .toList();

        Map<Boolean, List<ExtendedExpenseResponse>> partitionedUsers = expenseList.stream()
                                                                                  .sorted(Comparator.comparingDouble(ExtendedExpenseResponse::getIndvShareAmount)
                                                                                                    .reversed())
                                                                                  .collect(Collectors.partitioningBy(ExtendedExpenseResponse::isOweStatus));

        List<ExtendedExpenseResponse> getbackUsers = partitionedUsers.get(true);
        List<ExtendedExpenseResponse> topayUsers = partitionedUsers.get(false);

        for (ExtendedExpenseResponse getbackUser : getbackUsers)
        {
            for (ExtendedExpenseResponse topayUser : topayUsers)
            {
                if (topayUser.isPaymentCompleted())
                {
                    continue;
                }
                double balanceRem = getbackUser.getPendingOweAmount() > 0 ?
                                            getbackUser.getPendingOweAmount() :
                                            getbackUser.getIndvShareAmount();
                double donateBalRem = topayUser.getLeftOverBalForDonate() > 0 ?
                                              topayUser.getLeftOverBalForDonate() :
                                              topayUser.getIndvShareAmount();

                double calculatedBalRem = balanceRem - donateBalRem;
                if (calculatedBalRem == 0)
                {
                    // all paid for this user
                    getbackUser.setPendingOweAmount(0);
                    getbackUser.setPaymentCompleted(true);
                    populatePayorAndPayeeDetails(getbackUser, topayUser, donateBalRem);
                    topayUser.setPaymentCompleted(true);
                    //                    topayUsers.remove(topayUser);
                    break;
                }
                else if (calculatedBalRem > 0)
                {
                    // current topay user does not have enough money to pay
                    // check for other payor
                    getbackUser.setPendingOweAmount(calculatedBalRem);
                    getbackUser.setPaymentCompleted(false);
                    populatePayorAndPayeeDetails(getbackUser, topayUser, donateBalRem);
                    topayUser.setPaymentCompleted(true);
                    //                    topayUsers.remove(topayUser);
                }
                else
                {
                    // when the to pay user has more amount than the person owing
                    calculatedBalRem = calculatedBalRem * (-1);
                    // this payee is done. everything paid.
                    topayUser.setLeftOverBalForDonate(calculatedBalRem);
                    getbackUser.setPendingOweAmount(0);
                    getbackUser.setPaymentCompleted(true);
                    populatePayorAndPayeeDetails(getbackUser, topayUser, balanceRem);
                    break;
                }

            }
        }
        return Stream.concat(getbackUsers.stream(), topayUsers.stream())
                     .toList();
    }


}
