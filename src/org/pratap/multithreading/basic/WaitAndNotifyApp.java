package org.pratap.multithreading.basic;

/*
 * used intrinsic lock in case of achieving the Producer-Consumer
 * 
 */
class Processor {

	public void produce() throws InterruptedException {

		synchronized (this) {
			System.out.println("we are in the produce() method..");
			wait();
			System.out.println("Again produce() mehtod");
		}

	}

	public void consume() throws InterruptedException {

		Thread.sleep(1000);
		synchronized (this) {
			System.out.println("consume() method...");
			notify();
			Thread.sleep(3000);
		}
	}

}

public class WaitAndNotifyApp {

	public static void main(String[] args) {

		Processor processor = new Processor();
		
		Thread t1 = new Thread( () -> {
			try {
				processor.produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		Thread t2 = new Thread( () -> {
			try {
				processor.consume();
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
