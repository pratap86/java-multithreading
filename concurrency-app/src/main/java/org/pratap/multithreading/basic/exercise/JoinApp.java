package org.pratap.multithreading.basic.exercise;

public class JoinApp {

    public static void main(String[] args) {

        Thread threadOne = new Thread(() -> {
           for(int i = 0; i < 10; i++){
               try {
                   Thread.sleep(10);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("Runner1: "+i);
           }
        });

        Thread threadTwo = new Thread(() -> {
            for(int i = 0; i < 10; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Runner2: "+i);
            }
        });

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished the main Thread.");
    }
}
