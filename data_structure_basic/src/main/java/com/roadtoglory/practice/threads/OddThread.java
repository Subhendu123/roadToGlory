/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.practice.threads;
/*
*
*

This class OddThread is created and managed by subhe
Created on 13-01-2026 at 22:00 for the project Udemy Thread Learning

*
*
*/

public class OddThread extends Thread {

    public OddThread(String name) {
        super(name);
    }

    public void run() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 1) {
                System.out.println(i);
                count++;
            }
            if (count > 5) {
                break;
            }
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                System.out.println("Thread interrupted");
                break;
            }
        }
    }
}
