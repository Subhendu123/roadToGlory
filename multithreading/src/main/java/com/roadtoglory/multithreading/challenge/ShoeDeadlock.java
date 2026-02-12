/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.multithreading.challenge;
/*
*
*

This class ShoeDeadlock is created and managed by subhe
Created on 31-01-2026 at 08:39 for the project Udemy Challenge MT

*
*
*/

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShoeDeadlock {

    public static void main(String[] args) throws InterruptedException {

        Long t = 0L;
        ExecutorService taskExecutor = Executors.newFixedThreadPool(20);

        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();
        List<Runnable> taskList = List.of(new Producer(shoeWarehouse), new Consumer(shoeWarehouse), new Consumer(shoeWarehouse),
                new Producer(shoeWarehouse), new Consumer(shoeWarehouse), new Consumer(shoeWarehouse),
                new Producer(shoeWarehouse), new Consumer(shoeWarehouse), new Consumer(shoeWarehouse));
            for(Runnable task: taskList) {
                taskExecutor.submit(task);
            }
            t = System.currentTimeMillis();
            System.out.println("Shutting Down..." );
            Thread.sleep(30);
            taskExecutor.shutdown();
            System.out.println("Awaiting Termination... Submitting the below tasks ");
                for(Runnable task: taskList) {
                    System.out.println("Task "+task.toString());
                    // this task will not be accepted as the shutdown is invoked
                    //Exception in thread "main" java.util.concurrent.RejectedExecutionException:
                    // Task java.util.concurrent.FutureTask@53d8d10a[Not completed, task = java.util.concurrent.Executors$RunnableAdapter@6ce253f1
                    // [Wrapped task = com.roadtoglory.multithreading.challenge.Producer@1be6f5c3]] rejected from java.util.concurrent.ThreadPoolExecutor@e9e54c2
                    // [Shutting down, pool size = 9, active threads = 9, queued tasks = 0, completed tasks = 0]
                    //	at java.base/java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2065)
                    //	at java.base/java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:833)
                    //	at java.base/java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1365)
                    //	at java.base/java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:123)
                    //	at com.roadtoglory.multithreading.challenge.ShoeDeadlock.main(ShoeDeadlock.java:43)

//                    taskExecutor.submit(task);
                }
            taskExecutor.awaitTermination(20, TimeUnit.MILLISECONDS);
            t = (System.currentTimeMillis() - t) / 1000;
            System.out.println("Terminated After "+ t + " sec.");
    }

    public static void SingleThExecSvc(String[] args) {
        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();
        ExecutorService producerExecutor = Executors.newSingleThreadExecutor();
        ExecutorService consumerEx = Executors.newSingleThreadExecutor();
        ExecutorService consumerEx2 = Executors.newSingleThreadExecutor();

        producerExecutor.execute(new Producer(shoeWarehouse));
        consumerEx2.execute(new Consumer(shoeWarehouse));
        consumerEx.execute(new Consumer(shoeWarehouse));

        producerExecutor.shutdown();
        consumerEx.shutdown();
        consumerEx2.shutdown();

    }

    public static void normalThreadCreationTechnique(String[] args) {
        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();
        Thread producer = new Thread(new Producer(shoeWarehouse), "producer");
        Thread consumer1 = new Thread(new Consumer(shoeWarehouse), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer(shoeWarehouse), "Consumer-2");
        producer.start();
        consumer1.start();
        consumer2.start();
    }
}
