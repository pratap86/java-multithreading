package org.pratap.multithreading.basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Holds, Lock object, Condition object
 * 
 * @author 835698
 *
 */
class Worker {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void producer() throws InterruptedException {

		lock.lock();
		System.out.println("Producer method...");
		condition.await();
		System.out.println("Producer method again...");
		lock.unlock();
	}

	public void consumer() throws InterruptedException {

		lock.lock();
		Thread.sleep(2000);
		System.out.println("Consumer method...");
		condition.signal();
		lock.unlock();

	}
}

public class ProducerConsumerLockApp {

	public static void main(String[] args) {

		Worker worker = new Worker();

		Thread t1 = new Thread(() -> {

			try {
				worker.producer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		});

		Thread t2 = new Thread(() -> {

			try {
				worker.consumer();
			} catch (InterruptedException e) {
				e.printStackTrace();
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
	}

}
