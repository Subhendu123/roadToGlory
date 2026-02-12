package com.roadtoglory.multithreading.reentrant;


/*
*
*

    This class com.roadtoglory.multithreading.reentrant.BoundedBuffer is created and managed by subhe
    Created on 05-02-2026 at 21:52 for the project multithreading

*
*
*/


import java.sql.Time;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer<T> {

    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public void put(T item) throws InterruptedException {
//lock.tryLock(200, TimeUnit.MILLISECONDS);
        lock.lock();
        try {
            while(queue.size() == capacity) {
                System.out.println("Waiting for the writer lock by the thread "+Thread.currentThread().getName());
                notFull.await();
            }
            Thread.sleep(700);
            queue.add(item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while(queue.isEmpty()) {
                System.out.println("Waiting for the lock by the thread "+Thread.currentThread().getName());
                notEmpty.await();
            }
            T retVal =queue.poll();
            Thread.sleep(500);
            notFull.signal();

            return retVal;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(5);

        Thread p1 = new Thread(()-> {
            for (int i=0;i< boundedBuffer.capacity+5;i++){
                try {
//                    System.out.println("1. Item is written "+i*5);
                    boundedBuffer.put(i*5);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        },"Producer-1");

        Thread p2 = new Thread(()-> {
            for (int i=0;i< boundedBuffer.capacity+5;i++){
                try {
//                    System.out.println("2. Item is written "+i*5);
                    boundedBuffer.put(i*5);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        },"Producer-2");

        Thread p3 = new Thread(()-> {
            for (int i=0;i< boundedBuffer.capacity+5;i++){
                try {
//                    System.out.println("3. Item is written "+i*5);
                    boundedBuffer.put(i*5);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        },"Producer-3");

        Thread c1 = new Thread(()-> {
            for (int i=0;i< boundedBuffer.capacity+5;i++){
                try {
                    boundedBuffer.take();
//                    System.out.println("1. Item is read "+boundedBuffer.take());
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"Consumer-1");
        Thread c2 = new Thread(()-> {
            for (int i=0;i< boundedBuffer.capacity+5;i++){
                try {
                    boundedBuffer.take();

//                    System.out.println("2. Item is read "+boundedBuffer.take());
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"Consumer-2");

        Thread c3 = new Thread(()-> {
            for (int i=0;i< boundedBuffer.capacity+5;i++){
                try {
                    boundedBuffer.take();
//                    System.out.println("3. Item is read "+boundedBuffer.take());
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"Consumer-3");
        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();

        while (p1.isAlive() || p2.isAlive() || p3.isAlive() || c1.isAlive() || c2.isAlive() || c3.isAlive())
        {
            // not done
        }

        System.out.println("Completed.");
    }

}