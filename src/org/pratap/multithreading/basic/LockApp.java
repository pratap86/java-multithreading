package org.pratap.multithreading.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Producer-Consumer, with help of Lock interface;
 * 
 * ReentrantLock - lock(), unlock(), tryLock(), lockInterruptibly(), getHoldCount(), isHeldByCurrentThread()
 * 
 * It has the same behavior as 'synchronization approach', but has some additional behaviors;
 * 
 * 1.new ReentrantLock(boolean fairnessParameter), if it is set to be true, the longest-waiting thread
 * will get the lock.
 * 
 * if fairnessParamet is set to be false, there is no access order.
 * 
 * 2. a drawback;
 * 
 * to release the lock, must call the unlock() in finally block, otherwise current thread reaches to the Deadlock situation.
 * we have to use try-catch block, when doing the critical section that may throw the exception;
 * 	- must call the unlock() within the try-catch block
 * 
 * @author Pratap Narayan
 * @since
 *
 */
public class LockApp {

	private static int counter = 0;
	private static Lock lock = new ReentrantLock();
	
	private static void increment() {
		
		lock.lock();
		try {
			for( int i = 0; i < 10000; i++ ) {
				counter++;
			}
		} finally {
			lock.unlock();
		}
		
	}
	
	public static void main(String[] args) {

		Thread t1 = new Thread( () -> {
				increment();
		});
		
		Thread t2 = new Thread( () -> {
				increment();
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("counter : "+counter);
		
	}

}
