/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.practice.threads;
/*
*
*

This class ThreadJoin is created and managed by subhe
Created on 13-01-2026 at 08:24 for the project Thread Learning from Udemy

*
*
*/

public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LocalThread();
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
//                    System.out.println(" 1 : state: " + Thread.currentThread().getState());
                    System.out.print(". ");
                    Thread.sleep(500);
                }
                catch (InterruptedException e) {
                    System.out.println("Interrupted and the state: " + Thread.currentThread().getState());
                    return;// ✅ terminate run()
                }
            }
            System.out.println("Completed 1 ");
        });
//        t1.start();
//        System.out.println("t2 state just about to start: " + t2.getState()); // new
        t2.start();
//        System.out.println("t2 state just started: " + t2.getState()); // Runnable
        t2.join(5000);
        if (t2.isAlive()) {
            System.out.println("\nStill running after 5 secs. Interrupting it now");
            t2.interrupt();
        }
       /* Long start = System.currentTimeMillis();
        while (t2.isAlive()) {
            System.out.println(" M " + t2.getState());
//            Thread.sleep(500);
            System.out.println("State of t2 in Main " + t2.getState());
            if (System.currentTimeMillis() - start > 5000) {
                t2.interrupt();
            }
        }*/
        System.out.println("M completed. And t2 state: " + t2.getState());


    }
}
