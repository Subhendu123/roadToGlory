/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.multithreading.challenge;
/*
*
*

This class Record is created and managed by subhe
Created on 31-01-2026 at 08:14 for the project Udemy Challenge on MultiThreading 

*
*
*/

public class Order {

    private int orderId;
    private String shoeType;
    private int quantity;

    public String getShoeType() {
        return shoeType;
    }

    public void setShoeType(String shoeType) {
        this.shoeType = shoeType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
