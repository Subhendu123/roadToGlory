package com.roadtoglory.splitwiseexptracker.helper;

import com.roadtoglory.splitwiseexptracker.constants.SplitMethod;
import com.roadtoglory.splitwiseexptracker.dto.ExpenseDetailsDto;
import com.roadtoglory.splitwiseexptracker.dto.SplitInfoDto;
import com.roadtoglory.splitwiseexptracker.models.Expense;
import com.roadtoglory.splitwiseexptracker.models.UserExpense;


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
}
