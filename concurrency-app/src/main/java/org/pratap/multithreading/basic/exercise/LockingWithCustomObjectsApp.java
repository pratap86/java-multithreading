package org.pratap.multithreading.basic.exercise;

public class LockingWithCustomObjectsApp {

    private static Integer counterOne = 0;
    private static Integer counterTwo = 0;

    private static final Object lockOne = new Object();
    private static final Object lockTwo = new Object();

    private static  void incrementOne(){

        synchronized (lockOne){
            counterOne++;
        }
    }

    private static  void incrementTwo(){

        synchronized (lockTwo){
            counterTwo++;
        }
    }

    public static void process(){

        Thread t1 = new Thread(() -> {
           for (int i = 0; i < 100; ++i){
               incrementOne();
           }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; ++i){
                incrementTwo();
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
        System.out.println("The Counter - 1 is : "+counterOne);
        System.out.println("The Counter - 2 is : "+counterTwo);
    }

    public static void main(String[] args) {
        process();
    }
}
