package org.pratap.diningphilosophor.problem;

import java.util.Random;

public class Philosopher implements Runnable {

	private int id;
	private Chopstick leftChopstick;
	private Chopstick rightChopstick;
	private Random random;// a philosopher would take a random amt to thinking & eating
	private int eatingCounter;// how many times is taken by given philosopher for eating
	private volatile boolean isFull = false;
	
	public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
		this.id = id;
		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;
		this.random = new Random();
	}

	@Override
	public void run() {

		try {
			
			while (!isFull) {
				
				think();
				
				if(leftChopstick.pickUp(this, State.LEFT)) {
					
					if (rightChopstick.pickUp(this, State.RIGHT)) {
						eat();
						rightChopstick.putDown(this, State.RIGHT);
					}
					leftChopstick.putDown(this, State.LEFT);
					
				}
				
			}
		} catch (Exception e) {
			
		
		}
	}
	
	private void think() throws InterruptedException {
		System.out.println(this+" is thinking...");
		Thread.sleep(random.nextInt(1000));
	}
	
	private void eat() throws InterruptedException {
		System.out.println(this+" is eating...");
		this.eatingCounter++;
		Thread.sleep(random.nextInt(1000));
	}
	
	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
	
	public int getCounter() {
		return this.eatingCounter;
	}

	@Override
	public String toString() {
		return " Philosopher "+id;
	}
	
}
