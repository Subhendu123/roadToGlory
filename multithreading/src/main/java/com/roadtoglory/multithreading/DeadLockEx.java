/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.multithreading;

/*
*
*

This class DeadLockEx is created and managed by subhe
Created on 30-01-2026 at 08:33 for the project Udemy Threads

*
*
*/
public class DeadLockEx {

    public static void main(String[] args) throws InterruptedException {
        MessageRepo repoObject = new MessageRepo();
        Thread readerThread = new Thread(new MessageReader(repoObject), "Reader");
        Thread writerThread = new Thread(new MessageWriter(repoObject), "Writer");
        readerThread.start();
        Thread.sleep(2000);
        System.out.println("Starting the writer thread");
        writerThread.start();
    }


}
