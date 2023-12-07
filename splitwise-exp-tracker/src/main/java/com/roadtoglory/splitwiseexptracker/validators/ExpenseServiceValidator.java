package com.roadtoglory.splitwiseexptracker.validators;

import com.roadtoglory.splitwiseexptracker.constants.SplitMethod;
import com.roadtoglory.splitwiseexptracker.dto.ExpenseDetailsDto;
import com.roadtoglory.splitwiseexptracker.dto.SplitInfoDto;
import com.roadtoglory.splitwiseexptracker.exceptions.BadSplitInformationException;
import com.roadtoglory.splitwiseexptracker.exceptions.IncompleteRequestException;
import com.roadtoglory.splitwiseexptracker.models.Expense;

import java.util.List;
import java.util.stream.Collectors;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class ExpenseServiceValidator
{

    //    public static <SplitInfoDto> Predicate<com.roadtoglory.splitwiseexptracker.dto.SplitInfoDto> distinctByKey(Function<? super com.roadtoglory.splitwiseexptracker.dto.SplitInfoDto, ?> keyExtractor) {
    //        Set<Object> seen = ConcurrentHashMap.newKeySet();
    //        return t -> seen.add(keyExtractor.apply(t));
    //    }
    public static void validateExpenseDto (ExpenseDetailsDto expenseDetailsDto)
    {
        boolean isIncorrectSplitDetected = false;

        if (expenseDetailsDto != null && expenseDetailsDto.getExpenseDetails() != null && expenseDetailsDto.getSplitInfoDtos() != null)
        {
            List<SplitInfoDto> splitInfoDtos = expenseDetailsDto.getSplitInfoDtos();
            int count = splitInfoDtos.stream().map(splitInfoDto -> splitInfoDto.getUserId()).collect(Collectors.toSet()).size();

            //            Long count = splitInfoDtos.stream().filter(distinctByKey(SplitInfoDto::getUserId)).count();

            validateAndFlagSplitModeInfo(count != splitInfoDtos.size());

            Expense expenseDto = expenseDetailsDto.getExpenseDetails();

            validateExpenseDto(expenseDto);

            validateAndFlagSplitModeInfo(expenseDto.getSplitMethod() == null);

            if (expenseDto.getSplitMethod().equals(SplitMethod.EQUAL))
            {
                isIncorrectSplitDetected = expenseDetailsDto.getSplitInfoDtos().stream().anyMatch(s -> s.getCustomAmount() > 0 || s.getPercentage() > 0 || s.getShare() > 0);
                validateAndFlagSplitModeInfo(isIncorrectSplitDetected);
            }
            else if (expenseDto.getSplitMethod().equals(SplitMethod.SHARE))
            {
                isIncorrectSplitDetected = expenseDetailsDto.getSplitInfoDtos().stream().anyMatch(s -> s.getShare() < 0 || s.getCustomAmount() > 0 || s.getPercentage() > 0);
                validateAndFlagSplitModeInfo(isIncorrectSplitDetected);
            }
            else if (expenseDto.getSplitMethod().equals(SplitMethod.PERCENTAGE))
            {
                isIncorrectSplitDetected = expenseDetailsDto.getSplitInfoDtos().stream().anyMatch(s -> s.getShare() > 0 || s.getCustomAmount() > 0 || s.getPercentage() < 0);
                validateAndFlagSplitModeInfo(isIncorrectSplitDetected);

                double grandTotal = 0;
                for (SplitInfoDto individual : expenseDetailsDto.getSplitInfoDtos())
                {
                    grandTotal = grandTotal + individual.getPercentage();
                }
                validateAndFlagSplitModeInfo(grandTotal != 100);
            }
            else if (expenseDto.getSplitMethod().equals(SplitMethod.CUSTOM))
            {
                isIncorrectSplitDetected = expenseDetailsDto.getSplitInfoDtos().stream().anyMatch(s -> s.getShare() > 0 || s.getCustomAmount() < 0 || s.getPercentage() > 0);
                validateAndFlagSplitModeInfo(isIncorrectSplitDetected);

                double grandTotal = 0;
                for (SplitInfoDto individual : expenseDetailsDto.getSplitInfoDtos())
                {
                    grandTotal = grandTotal + individual.getCustomAmount();
                }
                validateAndFlagSplitModeInfo(grandTotal != expenseDto.getTotalAmount());
            }
        }
        else
        {
            throw new IncompleteRequestException("Incomplete Request! Please verify the expense details in the " + "request parameters!");
        }
    }

    private static void validateExpenseDto (Expense expenseDto)
    {
        if (expenseDto.getTotalAmount() <= 0)
        {
            throw new BadSplitInformationException("Total Amount in an expense cannot be less than or equal to zero");
        }
        if (expenseDto.getCategory() == null || expenseDto.getCurrencyCode() == null || expenseDto.getDescription() == null)
        {
            throw new BadSplitInformationException("Mandatory parameters are not available in the request");
        }
        if (expenseDto.getCreatedById() == 0 || expenseDto.getGroupId() == 0)
        {
            throw new BadSplitInformationException("Mandatory parameters are not available in the request");
        }
        if (expenseDto.getSplitMethod() == null || expenseDto.getDescription().isEmpty() || expenseDto.getCurrencyCode().isEmpty())
        {
            throw new BadSplitInformationException("Mandatory parameters are either not available or empty in the " + "request");
        }

    }

    public static void validateAndFlagSplitModeInfo (boolean isIncorrectSplitDetected)
    {
        if (isIncorrectSplitDetected)
        {
            throw new BadSplitInformationException("Incorrect Information! Please verify the request " + "parameters!");
        }
    }

    public static void validateOrRaiseExc (boolean isIncorrectSplitDetected, String exceptionMessage)
    {
        if (isIncorrectSplitDetected)
        {
            throw new BadSplitInformationException(exceptionMessage);
        }
    }
}
