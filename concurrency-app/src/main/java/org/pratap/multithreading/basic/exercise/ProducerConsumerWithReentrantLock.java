package org.pratap.multithreading.basic.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ProcessorLock {

    private List<Integer> integers = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private int value = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void producer() throws InterruptedException {

        try {
            lock.lock();

            while (true) {

                if (integers.size() == UPPER_LIMIT) {
                    System.out.println("producer() waiting for removing items...");
                    condition.await();
                } else {
                    System.out.println("Adding : "+value);
                    integers.add(value);
                    value++;
                    condition.signal();
                }
                Thread.sleep(500);
            }
        } finally {
            lock.unlock();
        }
    }

    public void consumer() throws InterruptedException{
        try {
            lock.lock();

            while (true) {

                if (integers.size() == LOWER_LIMIT) {
                    System.out.println("consumer() waiting for adding items...");
                    value = 0;
                    condition.await();
                } else {
                    System.out.println("Removing : "+integers.remove(integers.size()-1));
                    condition.signal();
                }
                Thread.sleep(500);
            }
        } finally {
            lock.unlock();
        }
    }
}

public class ProducerConsumerWithReentrantLock {

    public static void main(String[] args) {
        ProcessorLock processor = new ProcessorLock();

        Thread t1 = new Thread(() -> {
            try {
                processor.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
