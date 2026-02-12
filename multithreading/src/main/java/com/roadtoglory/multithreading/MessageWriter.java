/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.multithreading;
/*
*
*

This class MessageReader is created and managed by subhe
Created on 30-01-2026 at 08:35 for the project Udemy Deadlock

*
*
*/

public class MessageWriter implements Runnable {


    private MessageRepo messageRepo;

    public MessageWriter(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("In Writer nerthod");
            this.messageRepo.populateMsg();
            System.out.println("The message is recei");
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
