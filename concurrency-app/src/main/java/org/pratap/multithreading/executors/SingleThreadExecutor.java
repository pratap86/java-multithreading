package org.pratap.multithreading.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable{

    private int id;

    public Task(int id){
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task with id : "+id+" is in work - thread id: "+Thread.currentThread().getId());
        long duration = (long) (Math.random() * 5);

        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
public class SingleThreadExecutor {

    public static void main(String[] args) {

        // This executor has single thread so we can execute processes in a sequential manner. Every process is executed by a new thread.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for(int i = 0; i < 100; i++){
            executorService.submit(new Work(i));
        }
        // shut down the executor service
        executorService.shutdown();

    }
}
