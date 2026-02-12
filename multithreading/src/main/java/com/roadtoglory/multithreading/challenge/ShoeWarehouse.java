/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.multithreading.challenge;
/*
*
*

This class ShoeWarehouse is created and managed by subhe
Created on 31-01-2026 at 08:17 for the project Udemy Challenge on MT

*
*
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoeWarehouse {

    public static List<String> products = Arrays.asList(new String[]{"Nike", "Adidas", "Puma", "Campus", "Sketchers"});

    private List<Order> orders = new ArrayList<>();

    public synchronized void receiveOrder(Order order) {
        while (orders.size() > 10) {
            try {
//                Thread.sleep(500);
//                System.out.println("Received 10 orders now, wait....");
                wait();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        orders.add(order);
        notifyAll();
        System.out.println("[ "+Thread.currentThread().getName()+
                "] Order received for " + order.getOrderId() );
    }

    public synchronized void fulfillOrder() {
        while (orders.isEmpty()) {
            try {
//                System.out.println(
//                        "No orders in the queue. Evaluated by the thread " + Thread.currentThread().getName());
                wait();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Order order = orders.remove(0);
        notifyAll();
        System.out.println("[ "+Thread.currentThread().getName()+
                "] Order executed for " + order.getOrderId());
    }
}
