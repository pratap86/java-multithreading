package org.pratap.multithreading.basic.exercise;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DeadLock occurs when two or more threads wait forever for a lock or resource held by another threads.
 */
public class DeadLockApp {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public void workerOne(){

        lock1.lock();
        System.out.println("workerOne acquired the lock1...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock2.lock();
        System.out.println("workerOne acquired the lock2...");
        lock1.unlock();
        lock2.unlock();
    }

    public void workerTwo(){

        lock2.lock();
        System.out.println("workerTwo acquired the lock2...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock1.lock();
        System.out.println("workerTwo acquired the lock1...");
        lock2.unlock();
        lock1.unlock();
    }


    public static void main(String[] args) {

        DeadLockApp deadLockApp = new DeadLockApp();

        new Thread(deadLockApp::workerOne, "worker-1").start();
        new Thread(deadLockApp::workerTwo, "worker-2").start();
    }
}
