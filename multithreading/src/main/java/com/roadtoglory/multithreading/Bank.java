/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.multithreading;
/*
*
*

This class Bank is created and managed by subhe
Created on 27-01-2026 at 20:49 for the project Udemy Multi Threading

*
*
*/

public class Bank {

    private double balance;

    public Bank(double amount) {
        this.balance = amount;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        try {
            System.out.println("Talking to the teller...");
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (this) {
            double orgBal = this.balance;
            this.balance += amount;
            System.out.println(
                    amount + " Deposited into the account. Old Balance= " + orgBal + " New Available Balance= " + this.balance);
        }

    }

    public synchronized void withdraw(double amount) {
        double orgBal = this.balance;

        if (this.balance < amount) {
            System.out.println("Insuffienct Funds");
            return;
        }
        this.balance -= amount;
        System.out.println(
                amount + " Withdrawn from the account. Old Balance= " + orgBal + " New Available Balance= " + this.balance);
    }
}
