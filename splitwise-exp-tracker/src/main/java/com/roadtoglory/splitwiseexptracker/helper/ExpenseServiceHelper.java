package com.roadtoglory.splitwiseexptracker.helper;

import com.roadtoglory.splitwiseexptracker.constants.SplitMethod;
import com.roadtoglory.splitwiseexptracker.dto.ExpenseDetailsDto;
import com.roadtoglory.splitwiseexptracker.dto.ExtendedExpenseResponse;
import com.roadtoglory.splitwiseexptracker.dto.SplitInfoDto;
import com.roadtoglory.splitwiseexptracker.models.Expense;
import com.roadtoglory.splitwiseexptracker.models.UserExpense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class ExpenseServiceHelper
{


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


        // payment done by ind - is +
        List<UserExpense> indvUserExpList = users.stream()
                                                 .filter(userExpense -> userExpense.getUserShare() > 0)
                                                 .toList();
        //
        double totalLentAmtbyIndividual = expeListInGroup.stream()
                                                         .filter(expense -> expense.getPaidBy() == userId)
                                                         .mapToDouble(Expense::getTotalAmount)
                                                         .sum();

        //        for (UserExpense indvExp : indvUserExpList)
        //        {
        //            lentAmount = lentAmount + indvExp.getExpense().getTotalAmount() - indvExp.getUserShare();
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
