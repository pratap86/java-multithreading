package org.pratap.multithreading.basic.exercise;

import java.util.ArrayList;
import java.util.List;

class Processor {

    private List<Integer> integers = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void producer() throws InterruptedException {

        synchronized (lock) {
            while (true) {

                if (integers.size() == UPPER_LIMIT) {
                    System.out.println("producer() waiting for removing items...");
                    lock.wait();
                } else {
                    System.out.println("Adding : "+value);
                    integers.add(value);
                    value++;
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException{
        synchronized (lock) {
            while (true) {

                if (integers.size() == LOWER_LIMIT) {
                    System.out.println("consumer() waiting for adding items...");
                    value = 0;
                    lock.wait();
                } else {
                    System.out.println("Removing : "+integers.remove(integers.size()-1));
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}

public class ProducerConsumerWithSynchronization {

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
