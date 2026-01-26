/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.practice.threads;
/*
*
*

This class ThreadEx01 is created and managed by subhe
Created on 12-01-2026 at 19:53 for the project Thread Learning

*
*
*/

public class ThreadEx01 {

    public static void main(String[] args) {

        new LocalThread().start();

        Runnable runnableIntrfImpl = () -> {
            Thread.currentThread().setName("SUBH-RUN");
            System.out.println("Searching for a document");

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2000);
                    System.out.println((i * 10) + "% is Searched... ");
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Thread name " + Thread.currentThread().getName());
        };

        new Thread(runnableIntrfImpl).start();

        System.out.println("Downloading a document");
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(i * 20 + "% is Downloaded... ");

            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println("Thread name " + Thread.currentThread().getName());

    }
}
