package com.road2glory.splitwiseexpensetrackingapp.models;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class User {

    private String name;
    private long id;
    private double totalExpenses;

    private double myContributions;
    private double othersContributions;

    private List<ExpenseDetails> allExpenseDetails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public double getMyContributions() {
        return myContributions;
    }

    public void setMyContributions(double myContributions) {
        this.myContributions = myContributions;
    }

    public double getOthersContributions() {
        return othersContributions;
    }

    public void setOthersContributions(double othersContributions) {
        this.othersContributions = othersContributions;
    }

    public List<ExpenseDetails> getAllExpenseDetails() {
        return allExpenseDetails;
    }

    public void setAllExpenseDetails(List<ExpenseDetails> allExpenseDetails) {
        this.allExpenseDetails = allExpenseDetails;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", totalExpenses=" + totalExpenses +
                ", myContributions=" + myContributions +
                ", othersContributions=" + othersContributions +
                ", allExpenseDetails=" + allExpenseDetails +
                '}';
    }
}
