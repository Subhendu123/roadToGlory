/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.multithreading;
/*
*
*

This class MessageRepo is created and managed by subhe
Created on 30-01-2026 at 08:36 for the project Udemy Deadlock

*
*
*/

public class MessageRepo {
    String stringMessage = "When an object implementing interface {@code Runnable} is used\n" +
            "      to create a thread, starting the thread causes the object's\n" +
            "      {@code run} method to be called in that separately executing\n" +
            "      thread.";
    private boolean newMessageWaiting = false;
    private String message = null;

    public boolean isNewMessageWaiting() {
        return newMessageWaiting;
    }

    private void setNewMessageWaiting(boolean newMessageWaiting) {
        this.newMessageWaiting = newMessageWaiting;
    }

    public synchronized String populateMsg() {
        while (!isNewMessageWaiting()) {
//            System.out.println("Still not a new Message");
        }
        this.message = stringMessage;
        setNewMessageWaiting(false);
        return message;
    }


}
