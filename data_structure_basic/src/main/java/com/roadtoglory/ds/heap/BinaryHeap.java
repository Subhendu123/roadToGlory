/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.heap;
/*
*
*

This class BinaryHeap is created and managed by subhe
Created on 09-06-2026 at 19:52 for the project Udemy Heap Tutorial

*
*
*/

public class BinaryHeap {

    private static int currentCount = -1;
    private int[] heap;
    private boolean[] indexTracker;

    public BinaryHeap(int heapSize) {
        this.heap = new int[heapSize];
        this.indexTracker = new boolean[heapSize];
    }


    public void insert(int data, int parent) {
        if (currentCount == this.heap.length - 1) {
            return;
        }
        if (currentCount == -1) {
            this.heap[++currentCount] = data;
            this.indexTracker[currentCount] = true;
        }
        else {
            int parentIndex = getParentIndex(parent);
            if (parentIndex == -1) {
                throw new IllegalArgumentException("The Parent does not exist");
            }
            int leftIndex = 2 * parentIndex + 1;
            if (leftIndex < this.heap.length) {
                if (this.indexTracker[leftIndex]) {
                    if (leftIndex + 1 >= this.heap.length) {
                        throw new ArrayStoreException("Cannot store anymore data");
                    }

                    if (this.indexTracker[leftIndex + 1]) {
                        throw new ArrayStoreException("This parent already has both the children");
                    }
                    this.indexTracker[leftIndex + 1] = true;
                    this.heap[leftIndex + 1] = data;
                }
                else {

                    this.indexTracker[leftIndex] = true;
                    this.heap[leftIndex] = data;
                }
                return;
            }
            throw new ArrayStoreException("Cannot store anymore data");

        }

    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.heap.length; i++) {
            output.append(this.heap[i]);
            if (i != this.heap.length - 1) {
                output.append(" | ");
            }
        }
        return output.toString();
    }

    private int getParentIndex(int parent) {
        int parentIndex = -1;
        for (int i = 0; i < this.heap.length; i++) {
            if (this.heap[i] == parent) {
                parentIndex = i;
                break;
            }
        }
        return parentIndex;
    }
}
