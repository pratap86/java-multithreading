package org.pratap.diningphilosophor.problem;

import java.util.concurrent.TimeUnit;

/**
 * state of chopstick can be define by enum State (LEFT, RIGHT)
 * 
 * @author Pratap Naryaan
 *
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {

	private int id;

	private Lock lock;

	public Chopstick(int id) {
		this.id = id;
		this.lock = new ReentrantLock();
	}

	/**
	 * Two Philosopher are competing with chopsticks, pickup() method try to picked up chopstick within a time interval, otherwise return false.
	 * @param philosopher
	 * @param state
	 * @return
	 * @throws InterruptedException
	 */
	public boolean pickUp(Philosopher philosopher, State state) throws InterruptedException {
		
		if(lock.tryLock(10, TimeUnit.MILLISECONDS)) {
			System.out.println(philosopher+" picked up "+state.toString()+" "+this);
			return true;
		}
		return false;
	}
	
	/**
	 * put down the chopstick if the given philosopher has been eaten.
	 * @param philosopher
	 * @param state
	 * @throws InterruptedException
	 */
	public void putDown(Philosopher philosopher, State state) {
		
		lock.unlock();
		System.out.println(philosopher+" put down "+this);
	}

	@Override
	public String toString() {
		return " chopstick "+id;
	}
	
}
