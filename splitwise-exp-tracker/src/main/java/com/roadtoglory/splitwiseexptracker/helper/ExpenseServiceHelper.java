package com.roadtoglory.splitwiseexptracker.helper;

import com.roadtoglory.splitwiseexptracker.constants.SplitMethod;
import com.roadtoglory.splitwiseexptracker.dto.ExpenseDetailsDto;
import com.roadtoglory.splitwiseexptracker.dto.ExtendedExpenseResponse;
import com.roadtoglory.splitwiseexptracker.dto.SplitInfoDto;
import com.roadtoglory.splitwiseexptracker.models.Expense;
import com.roadtoglory.splitwiseexptracker.models.UserExpense;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/
public class ExpenseServiceHelper
{


    private static final Logger LOG = LogManager.getLogger(ExpenseServiceHelper.class);


    public static List<Expense> formatExpansesWithDto (int groupId, List<Expense> expenseResponseList, List<Expense> expenses)
    {
        expenses.stream().filter(expense -> {
            LOG.debug("SplitwiseExpTrackerApplication - The expense group id is " + expense.getGroupId());
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

    public static List<ExtendedExpenseResponse> evaluateExpenses (List<ExtendedExpenseResponse> expenseList)
    {
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
                    // all paid for this get user and the topay user is also exhaused
                    getbackUser.setPendingOweAmount(0);
                    getbackUser.setPaymentCompleted(true);
                    populatePayorAndPayeeDetails(getbackUser, topayUser, donateBalRem);
                    topayUser.setPaymentCompleted(true);
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
                    topayUser.setLeftOverBalForDonate(0);
                    topayUser.setPaymentCompleted(true);
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

    public static ExtendedExpenseResponse getExpenseResponseDto (int userId, int groupId, List<Expense> expeListInGroup)
    {

        List<UserExpense> users = expeListInGroup.stream()
                                                 .flatMap(s -> s.getUserExpenses()
                                                                .stream()
                                                                .filter(userExpense -> userExpense.getUserId() == userId))
                                                 .toList();

        double totalOwedAmtByIndividual = users.stream()
                                               .filter(userExpense -> userExpense.getUserShare() < 0)
                                               .mapToDouble(UserExpense::getUserShare)
                                               .sum();
        totalOwedAmtByIndividual = totalOwedAmtByIndividual * (-1);


        // payment done by ind - is + // totalling all the payments incl the one paid by the individual
        totalOwedAmtByIndividual += users.stream()
                                         .filter(userExpense -> userExpense.getUserShare() > 0)
                                         .mapToDouble(UserExpense::getUserShare)
                                         .sum();

        //
        double totalLentAmtbyIndividual = expeListInGroup.stream()
                                                         .filter(expense -> expense.getPaidBy() == userId)
                                                         .mapToDouble(Expense::getTotalAmount)
                                                         .sum();
        //
        //        for (UserExpense indvExp : indvUserExpList)
        //        {
        //            totalLentAmtbyIndividual = totalLentAmtbyIndividual + indvExp.getExpense()
        //                                                                         .getTotalAmount() - indvExp.getUserShare();
        //        }
        double getBackAmount = totalLentAmtbyIndividual - totalOwedAmtByIndividual;
        double totalExpense = expeListInGroup.stream().mapToDouble(Expense::getTotalAmount).sum();

        // calculate the expenses for the individual in the group
        double indExpense = 0;
        for (UserExpense userExpense : users)
        {
            indExpense = indExpense + (userExpense.getUserShare() < 0 ? userExpense.getUserShare() * -1 : userExpense.getUserShare());
        }

        ExtendedExpenseResponse responseDto = new ExtendedExpenseResponse();
        responseDto.setGroupId(groupId);
        responseDto.setUserId(userId);
        responseDto.setTotalGrpExpense(totalExpense);
        responseDto.setOweStatus(getBackAmount > -1);
        // total amount to be either received from group or paid to the group
        responseDto.setIndvShareAmount(getBackAmount > 0 ? getBackAmount : getBackAmount * -1);
        // total amount of the individual expense share
        responseDto.setTotalIndExpense(indExpense);

        return responseDto;
    }

    public static UserExpense getUserExpense (ExpenseDetailsDto expenseDetailsDto, Expense expense, double indvShare, SplitInfoDto splitInfoDto)
    {
        int totalShares = 0;
        if (expenseDetailsDto.getExpenseDetails().getSplitMethod().equals(SplitMethod.SHARE))
        {
            for (SplitInfoDto individual : expenseDetailsDto.getSplitInfoDtos())
            {
                totalShares = totalShares + individual.getShare();
            }
        }
        UserExpense userExpense = new UserExpense();
        userExpense.setActiveStatus(true);
        userExpense.setExpense(expense);
        userExpense.setUserId(splitInfoDto.getUserId());

        // individual share

        if (indvShare > -1)
        {
            setUserShare(expense, splitInfoDto, userExpense, indvShare);
        }
        else
        {
            if (expenseDetailsDto.getExpenseDetails().getSplitMethod().equals(SplitMethod.SHARE))
            {
                double userShareAmt = (splitInfoDto.getShare() / totalShares) * expense.getTotalAmount();
                setUserShare(expense, splitInfoDto, userExpense, userShareAmt);
            }
            else if (expenseDetailsDto.getExpenseDetails().getSplitMethod().equals(SplitMethod.PERCENTAGE))
            {
                double userShareAmt = (splitInfoDto.getPercentage() / 100) * expense.getTotalAmount();
                setUserShare(expense, splitInfoDto, userExpense, userShareAmt);
            }
            else
            {
                double userShareAmt = splitInfoDto.getCustomAmount();
                setUserShare(expense, splitInfoDto, userExpense, userShareAmt);
            }

        }
        return userExpense;
    }

    private static void setUserShare (Expense expense, SplitInfoDto splitInfoDto, UserExpense userExpense, double userShareAmt)
    {
        userShareAmt = expense.getPaidBy() == splitInfoDto.getUserId() ? userShareAmt : userShareAmt * (-1);
        userExpense.setUserShare(userShareAmt);
    }

    public static void populatePayorAndPayeeDetails (ExtendedExpenseResponse getbackUser, ExtendedExpenseResponse topayUser, double donateBalRem)
    {
        List<Map<Integer, Double>> payorList = getbackUser.getPayeeOrPayerList();
        if (payorList == null)
        {
            payorList = new ArrayList<>();
        }
        Map<Integer, Double> payor = new HashMap<>();
        payor.put(topayUser.getUserId(), donateBalRem);
        payorList.add(payor);
        getbackUser.setPayeeOrPayerList(payorList);

        // populate payee
        List<Map<Integer, Double>> payeeList = topayUser.getPayeeOrPayerList();
        if (payeeList == null)
        {
            payeeList = new ArrayList<>();
        }
        Map<Integer, Double> payee = new HashMap<>();
        payee.put(getbackUser.getUserId(), donateBalRem);
        payeeList.add(payee);
        topayUser.setPayeeOrPayerList(payeeList);
    }


}
