package org.pratap.multithreading.basic.exercise;

class Worker implements Runnable {

    // volatile just instruct the CPU, don't cache their value and always read from main memory
    private volatile boolean terminated;

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    @Override
    public void run() {

        while (!terminated){
            System.out.println("working class is running...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class VolatileApp {

    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        t1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.setTerminated(true);
        System.out.println("Worker Terminated");
    }
}
