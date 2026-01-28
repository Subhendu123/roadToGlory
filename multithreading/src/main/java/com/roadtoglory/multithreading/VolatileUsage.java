/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.multithreading;
/*
*
*

This class VolatileUsage is created and managed by subhe
Created on 27-01-2026 at 20:32 for the project Volatile Usage in Udemy 

*
*
*/

public class VolatileUsage {

    // volatile will ensure that the value would be read from the heap memory rather than the thread's memoery cache
    private boolean flag = false;

    public static void main(String[] args) {
        VolatileUsage volatileUsage = new VolatileUsage();


        Thread writerThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                volatileUsage.toggleFlag();
            }
            catch (InterruptedException e) {
            }
            System.out.println("The flag set to " + volatileUsage.isReady());

        });

        Thread readerThread = new Thread(() -> {
            while (!volatileUsage.isReady()) {

                // do nothing
            }
            System.out.println("The flag is " + volatileUsage.isReady());
        });

        writerThread.start();
        readerThread.start();
    }

    public void toggleFlag() {
        this.flag = !this.flag;
    }

    public boolean isReady() {
        return this.flag;
    }
}
