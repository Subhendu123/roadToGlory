/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.greedy_algo;
/*
*
*

This class KnapsackCapacity is created and managed by subhe
Created on 06-03-2026 at 07:33 for the project Udemy Learning

*
*
*/

import java.util.ArrayList;
import java.util.List;

public class FractionalKnapsack {
    List<Item> items = new ArrayList<>();

    public FractionalKnapsack() {

    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public int calculateOptimum(int capacity) {
        this.items = this.items.stream().sorted((item1, item2) -> item1.ratio() > item2.ratio() ? -1 : 1).toList();

        int result = 0;
        int currentCapacity = capacity;
        for (Item item : this.items) {

            if (currentCapacity == 0) {
                break;
            }

            if (item.weight <= currentCapacity) {
                result = result + item.value;
                currentCapacity = currentCapacity - item.weight;
            }
            else {
                int value = item.value * currentCapacity / item.weight;
                result += value;
                currentCapacity = currentCapacity - item.weight;
            }

        }
        return result;
    }


    public class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public int ratio() {
            return value / weight;
        }

        @Override
        public String toString() {
            return this.weight + " - " + this.value;
        }
    }
}
