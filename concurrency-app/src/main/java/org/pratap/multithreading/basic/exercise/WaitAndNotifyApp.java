package org.pratap.multithreading.basic.exercise;

class Process {

    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("Running the produce() method...");
            wait();
            System.out.println("Again in the produce() method...");
        }
    }

    public void consume() throws InterruptedException{
        Thread.sleep(1000);
        synchronized (this){
            System.out.println("consume() method started");
            notify();
        }
    }
}
public class WaitAndNotifyApp {
    public static void main(String[] args) {

        Process process = new Process();

        Thread t1 = new Thread(() -> {
            try {
                process.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                process.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
