/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.multithreading.challenge;
/*
*
*

This class Producer is created and managed by subhe
Created on 31-01-2026 at 08:40 for the project Udemy Challenge MT

*
*
*/

import java.util.Random;

public class Producer implements Runnable{

    private final int maxCapacity = 10;
    private ShoeWarehouse warehouse;

    Producer(ShoeWarehouse warehouse) {
        this.warehouse = warehouse;
    }


    /**
     *
     */
    @Override
    public void run() {
        for (int i = 0; i < this.maxCapacity; i++) {

            Order order = new Order();
            order.setOrderId(i + 1);
            order.setQuantity(10);
            order.setShoeType(ShoeWarehouse.products.get(new Random().nextInt(0, 5)));
            this.warehouse.receiveOrder(order);
            try {
                Thread.sleep(500);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
