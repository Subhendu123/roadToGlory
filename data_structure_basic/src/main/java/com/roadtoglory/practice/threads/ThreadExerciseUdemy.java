/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.practice.threads;
/*
*
*

This class ThreadExerciseUdemy is created and managed by subhe
Created on 13-01-2026 at 22:03 for the project Udemy Thread

*
*
*/

public class ThreadExerciseUdemy {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new OddThread("t1");
        Thread t2 = new Thread(() -> {
            int count = 0;
            for (int i = 0; i < 100; i++) {
                if (i > 0 && i % 2 == 0) {
                    System.out.println(i);
                    count++;
                }
                if (count > 5) {
                    break;
                }
                try {
                    Thread.sleep(200);
                }
                catch (InterruptedException e) {
                    System.out.println("Interr");
                    break;
                }
            }
        }, "t2");
        t1.start();
        t2.start();

        System.out.println("Sleeping for 2 secs");
        Thread.sleep(3000);
        t1.interrupt();
//        t2.interrupt();
    }
}
