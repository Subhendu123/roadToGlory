/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.multithreading.challenge;
/*
*
*

This class Consumer is created and managed by subhe
Created on 31-01-2026 at 08:40 for the project Udemy Challenge MT

*
*
*/

public class Consumer implements Runnable {
    private final int maxCapacity = 5;
    private ShoeWarehouse warehouse;

    Consumer(ShoeWarehouse warehouse) {
        this.warehouse = warehouse;
    }


    /**
     *
     */
    @Override
    public void run() {
        for (int i = 0; i < this.maxCapacity; i++) {
            this.warehouse.fulfillOrder();
            try {
                Thread.sleep(500);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
