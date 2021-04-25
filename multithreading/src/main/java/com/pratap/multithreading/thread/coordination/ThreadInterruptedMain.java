package com.pratap.multithreading.thread.coordination;

public class ThreadInterruptedMain {

	public static void main(String[] args) {

		Thread thread = new Thread(() ->{
			try {
				Thread.sleep(50000000);
			} catch (InterruptedException e) {
				System.out.println("Exiting from blocking thread..");
			}
		});
		
		thread.start();
		thread.interrupt();
	}

}
