package com.pratap.multithreading.deadlock;

import java.util.Random;
/**
 * <p>Traffic Control System, </p>
 * @author Pratap Narayan
 *
 */
public class DeadLockMain {

	public static void main(String[] args) {

		Intersection intersection = new Intersection();
		
		Thread threadA = new Thread(new TrainA(intersection));
		Thread threadB = new Thread(new TrainB(intersection));
		
		threadA.start();
		threadB.start();
	}
	
	public static class TrainA implements Runnable{

		private Intersection intersection;
		
		private Random random = new Random();
		
		public TrainA(Intersection intersection) {
			this.intersection = intersection;
		}

		@Override
		public void run() {
			
			while(true) {
				long sleepingTime = random.nextInt(5);
				try {
					Thread.sleep(sleepingTime);
				} catch (InterruptedException e) {
				}
				intersection.takeRoadA();
			}
		}
		
	}
	
	public static class TrainB implements Runnable{

		private Intersection intersection;
		
		private Random random = new Random();
		
		public TrainB(Intersection intersection) {
			this.intersection = intersection;
		}

		@Override
		public void run() {
			
			while(true) {
				long sleepingTime = random.nextInt(5);
				try {
					Thread.sleep(sleepingTime);
				} catch (InterruptedException e) {
				}
				intersection.takeRoadB();
			}
		}
		
	}

	/**
	 * <p>Intersection has the intersection of two roads A & B, and trains are passing through these two roads.
	 * No two trains are passes through these roads simultaneously, blocked two roads, one for passing train and another for another road.
	 * </p>
	 * @author Pratap Narayan
	 *
	 */
	public static class Intersection{
		private Object roadA = new Object();
		private Object roadB = new Object();
		
		public void takeRoadA() {
			synchronized (roadA) {
				System.out.println("road A is locked by thread "+Thread.currentThread().getName());
				
				synchronized (roadB) {
					System.out.println("Train is passing through road A");
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
					}
				}
			}
		}
		
		public void takeRoadB() {
			synchronized (roadB) { // to avoid the deadlock, use lock in same order
				System.out.println("road B is locked by thread "+Thread.currentThread().getName());
				
				synchronized (roadA) {
					System.out.println("Train is passing through road B");
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}
}
