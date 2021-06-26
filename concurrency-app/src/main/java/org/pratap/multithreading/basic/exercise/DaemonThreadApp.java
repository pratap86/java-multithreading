package org.pratap.multithreading.basic.exercise;

public class DaemonThreadApp {

    public static void main(String[] args) {

        Thread daemonThread = new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Daemon Thread is Running");
            }
        });

        Thread normalThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker thread Finished");
        });

        daemonThread.setDaemon(true);
        daemonThread.start();
        normalThread.start();

    }

}
