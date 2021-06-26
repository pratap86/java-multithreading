package org.pratap.multithreading.basic.exercise;

public class SynchronizationApp {

    private static Integer counter = 0;

    /**
     * we have to make sure this method is executed only by a single thread at a given time
     */
    private static synchronized void increment(){
        counter++;
    }

    public static void process(){

        Thread t1 = new Thread(() -> {
           for (int i = 0; i < 100; ++i){
               increment();
           }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; ++i){
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The Counter is : "+counter);
    }

    public static void main(String[] args) {
        process();
    }
}
