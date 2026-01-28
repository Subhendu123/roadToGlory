/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.multithreading;
/*
*
*

This class TellerApp is created and managed by subhe
Created on 27-01-2026 at 20:52 for the project Udemy Multi Threading

*
*
*/

public class TellerApp {

    public static void main(String[] args) {

        Bank bank = new Bank(5000);
        Thread t1 = new Thread(() -> {
            bank.deposit(6000);
        });
        Thread t2 = new Thread(() -> {
            bank.withdraw(4000);
        });
        Thread t3 = new Thread(() -> {
            bank.withdraw(2000);
        });
        Thread t4 = new Thread(() -> {
            bank.deposit(5000);
        });


        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(" The Final Balance " + bank.getBalance());
    }
}
