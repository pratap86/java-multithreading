package com.pratap.multithreading.questions.printnumbersbythreethreads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <blockquote>
 * Thread1	Thread2	Thread3
 * 	  1		  2		  3
 * 	  4		  5		  6
 * 	  7		  8		  9
 * 	  10..
 * 	  % 3=1	  %3=2	  %3=0
 * </blockquote>
 * @author Pratap Narayan
 *
 */
public class NumberGenerator {

	private int number = 1;
	private int numberOfThreads;
	private int totalNumbersInSequence;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public NumberGenerator(int numberOfThreads, int totalNumbersInSequence) {
		this.numberOfThreads = numberOfThreads;
		this.totalNumbersInSequence = totalNumbersInSequence;
	}
	
	/**
	 * 
	 * @param result = number % 3
	 */
	public void printNumber(int result) {
//		synchronized (this) {
			try {
				lock.lock();
				while (number < totalNumbersInSequence -1) {
					// only permits the particular thread prints the particular number in sequence
					while (number % numberOfThreads != result) {

						try {
//							wait();
							condition.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					System.out.println(Thread.currentThread().getName()+" "+number++);
//					notifyAll();
					condition.signalAll();
				}
			} finally {
				lock.unlock();
			}
//		}
	}
}
