package com.pratap.multithreading.challenges;

public class DataRaceMain {

	public static void main(String[] args) {

		SharedClass shared = new SharedClass();
		
		Thread thread1 = new Thread(() -> {
			for(int i = 0; i < Integer.MAX_VALUE; i++) {
				shared.increment();
			}
		});
		
		Thread thread2 = new Thread(() -> {
			for(int i = 0; i < Integer.MAX_VALUE; i++) {
				shared.checkForDataRace();
			}
		});
		
		thread1.start();
		thread2.start();
	}

	public static class SharedClass{
		private volatile int x = 0;
		private volatile int y = 0;
		
		public void increment() {
			x++;
			y++;
		}
		
		public void checkForDataRace() {
			if(y > x) {
				System.out.println("y > x - Data Race is detected");
			}
		}
	}
}
