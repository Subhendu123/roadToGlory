/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.hashing;
/*
*
*

This class DoubleHashing is created and managed by subhe
Created on 26-03-2026 at 07:00 for the project Udemy  Hashing Techniques

*
*
*/

import java.util.Arrays;

public class DoubleHashing {

    private int size = 0;
    private int[] elements;

    public void insert(int input) {

//        if (this.elements.length == this.size) {
//            return;
//        }

        int h1 = hash(input, true);
        int index = h1;
        int counter = 1;
        while (isOccupied(index)) {
            int offsetInd = counter + hash(input, false);
            index = (h1 + offsetInd) % this.size;
        }

        this.elements[index] = input;
        System.out.println("Element '" + input + "' added to the index " + index);
    }

    private boolean isOccupied(int index) {
        return this.elements[index] != Integer.MIN_VALUE ? true : false;
    }

    private int hash(int input, boolean isPrimary) {
        return isPrimary ? input % this.size : input % (this.size - 1);
    }

    public void initialize(int size) {
        this.size = size;
        this.elements = new int[size];
        Arrays.fill(elements, Integer.MIN_VALUE);
    }

    public void print() {
        System.out.println("Printing results");
        for (int element : this.elements) {
            System.out.print(element + ", ");
        }
    }
}
