package com.road2glory.splitwiseexpensetrackingapp.models;

import java.util.Date;

public class ExpenseDetails {

    private Date recordDate;
    private String description;
    private String category;
    private double amount;
    private boolean paidbyme;

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaidbyme() {
        return paidbyme;
    }

    public void setPaidbyme(boolean paidbyme) {
        this.paidbyme = paidbyme;
    }

    @Override
    public String toString() {
        return "ExpenseDetails{" +
                "recordDate=" + recordDate +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", paidbyme=" + paidbyme +
                '}';
    }
}
