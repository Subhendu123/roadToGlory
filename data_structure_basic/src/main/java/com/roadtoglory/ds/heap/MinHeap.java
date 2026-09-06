/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.heap;
/*
*
*

This class MinHeap is created and managed by subhe
Created on 09-06-2026 at 21:11 for the project Udemy Heap

*
*
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinHeap {

    private int[] heap;
    private int currentSize = 0;
    private int capacity;

    public MinHeap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.heap = new int[initialCapacity];
    }

    public void insert(int data) {
        if (capacity > currentSize) {
            this.heap[currentSize] = data;
            verifyAndSwap(data, currentSize);
            currentSize++;

        }
        else {
            capacity = capacity * 2;
            //reinit
            this.heap = Arrays.copyOf(this.heap, capacity);
            this.heap[currentSize] = data;
            verifyAndSwap(data, currentSize);
            currentSize++;
        }
    }

    private void verifyAndSwap(int data, int currentIndex) {
        if (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            int parent = this.heap[parentIndex];
            if (parent > data) {
                this.heap[parentIndex] = data;
                this.heap[currentIndex] = parent;
                if (parentIndex != 0) {
                    verifyAndSwap(data, parentIndex);
                }
            }

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

    public void printHeap() {
        List<Integer> minHeapList = new ArrayList<>();
        int[] tempHeap = Arrays.copyOf(this.heap, this.currentSize);
        int lastIndex = this.currentSize - 1;
        while (minHeapList.size() < tempHeap.length) {
            minHeapList.add(tempHeap[0]);
            tempHeap[0] = tempHeap[lastIndex];
            heapify(tempHeap, 0);
            tempHeap[lastIndex--] = -1;
        }
        System.out.println(minHeapList);

    }

    private void heapify(int[] tempHeap, int parent) {
        int c1 = 2 * parent + 1;
        int c2 = 2 * parent + 2;
        int minIdx = Math.min(c1, c2);
        if (tempHeap[parent] > tempHeap[minIdx]) {
            int oldMin = tempHeap[parent];
            tempHeap[parent] = tempHeap[minIdx];
            tempHeap[minIdx] = oldMin;
            heapify(tempHeap, minIdx);
        }
    }


}
