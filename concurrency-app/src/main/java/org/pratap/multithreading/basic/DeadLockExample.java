package org.pratap.multithreading.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author Pratap Narayan
 * @DeadLock - Two threads that blocked each other, Deadlock occurs when two or more threads wait
 * forever for a lock or shared resource held by another of the thread.
 * @Solution Handle Deadlock;
 * 1. using tryLock
 * 2. we should make sure the each thread acquire the locks in the same order, to avoid the any cyclic-
 *    dependency in the lock acquisition.
 * @Remark - could eliminate Deadlock, if we could eliminate cyclic dependency.
 */
public class DeadLockExample {

	private Lock lock1 = new ReentrantLock(true);
	
	private Lock lock2 = new ReentrantLock(true);
	
	public static void main(String[] args) {

		DeadLockExample deadlock = new DeadLockExample();
		new Thread(deadlock::worker1, "worker1").start();
		new Thread(deadlock::worker2, "worker2").start();
		
	}
	
	public void worker1() {
		lock1.lock();
		System.out.println("worker1 acquire the lock1...");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock2.lock();
		System.out.println("worker1 acquire the lock2...");
		
		lock1.unlock();
		lock2.unlock();
	}
	
	public void worker2() {
		lock1.lock();// same order
		System.out.println("worker2 acquire the lock1...");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock2.lock();//same order
		System.out.println("worker2 acquire the lock2...");
		
		lock2.unlock();
		lock1.unlock();
	}

}
