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

public class MessageReader implements Runnable {

    private MessageRepo messageRepo;

    public MessageReader(MessageRepo messageRepo) {
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
        String s = this.messageRepo.populateMsg();
        System.out.println("The message received is " + s);
    }
}
