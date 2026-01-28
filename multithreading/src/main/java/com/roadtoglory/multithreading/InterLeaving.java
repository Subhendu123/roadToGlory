/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.multithreading;
/*
*
*

This class InterLeaving is created and managed by subhe
Created on 27-01-2026 at 19:41 for the project Multi Threading Udemy

*
*
*/

public class InterLeaving {

    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {

                for (int i = 0; i < 10; i++) {
                    count++;
                    Thread.sleep(1);
                }
                System.out.println("T1 The final count value " + count);
            }
            catch (InterruptedException e) {
                System.out.println("Exception " + e.getMessage());
            }


        }, "T1");

        Thread t2 = new Thread(() -> {
            try {

                for (int i = 0; i < 10; i++) {
                    count++;
                    Thread.sleep(5);
                }
                System.out.println("T2 The final count value " + count);
            }
            catch (InterruptedException e) {
                System.out.println("Exception " + e.getMessage());
            }


        }, "T2");

        Thread t3 = new Thread(() -> {
            try {

                for (int i = 0; i < 10; i++) {
                    count++;
                    Thread.sleep(10);

                }
                System.out.println("T3 The final count value " + count);
            }
            catch (InterruptedException e) {
                System.out.println("Exception " + e.getMessage());
            }


        }, "T3");

        t1.start();
        t2.start();
        t3.start();
       /* t1.start();
        t1.join();
        System.out.println("...........T1 is done......");

        t2.start();
        t2.join();
        System.out.println("...........T2 is done......");

        t3.start();
        t3.join();
        System.out.println("...........T3 is done......");*/
        int checkCount = 0;
        while (t1.isAlive() || t2.isAlive() || t3.isAlive()) {
            checkCount++;
        }

        System.out.println("The Final Value of the Count after all of the execution is " + count);

    }
}
