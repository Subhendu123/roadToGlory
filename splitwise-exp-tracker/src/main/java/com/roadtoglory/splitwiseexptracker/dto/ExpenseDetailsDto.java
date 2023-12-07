package com.roadtoglory.splitwiseexptracker.dto;

import com.roadtoglory.splitwiseexptracker.models.Expense;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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
@Component
public class ExpenseDetailsDto
{
    private Expense expenseDetails;

//    private Map<Integer, Double> splitInfo;

    private List<SplitInfoDto> splitInfoDtos;

    public Expense getExpenseDetails ()
    {
        return expenseDetails;
    }

    public void setExpenseDetails (Expense expenseDetails)
    {
        this.expenseDetails = expenseDetails;
    }

    public List<SplitInfoDto> getSplitInfoDtos ()
    {
        return splitInfoDtos;
    }

    public void setSplitInfoDtos (List<SplitInfoDto> splitInfoDtos)
    {
        this.splitInfoDtos = splitInfoDtos;
    }
}
