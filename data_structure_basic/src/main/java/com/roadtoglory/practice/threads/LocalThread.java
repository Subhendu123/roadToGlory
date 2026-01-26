/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.practice.threads;
/*
*
*

This class LocalThread is created and managed by subhe
Created on 12-01-2026 at 19:49 for the project Thread learnings

*
*
*/

public class LocalThread extends Thread {


    public synchronized void start() {

        System.out.println("My own start");
        super.start();
    }


    public void run() {
        Thread.currentThread().setName("Local");
        System.out.println("Uploading a document of large size");
        for (int i = 0; i < 10; i++) {

            try {
                sleep(1000);
                System.out.println(i * 10 + "% is uploaded... ");
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println("Thread name " + Thread.currentThread().getName());

    }

}
