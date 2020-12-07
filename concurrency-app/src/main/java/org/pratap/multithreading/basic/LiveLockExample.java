package org.pratap.multithreading.basic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author Pratap Narayan
 * @LiveLock - If the other thread's action is also response to the action of
 *           another thread then livelock may occur. Livelocks threads are
 *           unable to make further progress. However, the threads are not
 *           blocked: they are simply too busy responding to each other to
 *           resume work.
 * 
 */
public class LiveLockExample {

	private Lock lock1 = new ReentrantLock(true);

	private Lock lock2 = new ReentrantLock(true);

	public static void main(String[] args) {

		LiveLockExample livelock = new LiveLockExample();
		new Thread(livelock::worker1, "worker1").start();
		new Thread(livelock::worker2, "worker2").start();

	}

	public void worker1() {

		while (true) {

			try {
				lock1.tryLock(50, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("worker1 acquired the lock1");
			System.out.println("worker1 trying to acquire the lock2...");

			if (lock2.tryLock()) {
				System.out.println("worker1 acquired the lock2");
				lock2.unlock();
			} else {
				System.out.println("worker1 can not acquire lock2...");
				continue;
			}
			break;
		}

		lock1.unlock();
		lock2.unlock();
	}

	public void worker2() {

		while (true) {

			try {
				lock2.tryLock(50, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("worker2 acquired the lock2");
			System.out.println("worker2 trying to acquire the lock1...");

			if (lock1.tryLock()) {
				System.out.println("worker2 acquired the lock1");
				lock1.unlock();
			} else {
				System.out.println("worker2 can not acquire lock1...");
				continue;
			}
			break;
		}

		lock2.unlock();
		lock1.unlock();
	}

}
