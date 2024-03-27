package com.roadtoglory.splitwiseexptracker.dto;

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
public class ExtendedExpenseResponse extends SimpleExpenseResponse
{


    private boolean paymentCompleted;
    private double pendingOweAmount;
    private double leftOverBalForDonate;
    private List<Map<Integer, Double>> payeeOrPayerList;

    public ExtendedExpenseResponse ()
    {
        super();
    }

    public boolean isPaymentCompleted ()
    {
        return paymentCompleted;
    }

    public void setPaymentCompleted (boolean paymentCompleted)
    {
        this.paymentCompleted = paymentCompleted;
    }

    public double getPendingOweAmount ()
    {
        return pendingOweAmount;
    }

    public void setPendingOweAmount (double pendingOweAmount)
    {
        this.pendingOweAmount = pendingOweAmount;
    }

    public double getLeftOverBalForDonate ()
    {
        return leftOverBalForDonate;
    }

    public void setLeftOverBalForDonate (double leftOverBalForDonate)
    {
        this.leftOverBalForDonate = leftOverBalForDonate;
    }

    public List<Map<Integer, Double>> getPayeeOrPayerList ()
    {
        return payeeOrPayerList;
    }

    public void setPayeeOrPayerList (List<Map<Integer, Double>> payeeOrPayerList)
    {
        this.payeeOrPayerList = payeeOrPayerList;
    }


}
