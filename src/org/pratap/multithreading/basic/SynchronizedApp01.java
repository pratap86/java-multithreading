package org.pratap.multithreading.basic;

public class SynchronizedApp01 {

	private static int counter = 0;

	private synchronized static void increment() {
		++counter;
	}
	public static void process() {

		Thread t1 = new Thread(() -> {

			for (int i = 0; i < 1000; ++i) {
				increment();
			}

		});

		Thread t2 = new Thread(() -> {

			for (int i = 0; i < 1000; ++i) {
				increment();
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


	public static void main(String[] args) {

		process();
		System.out.println(counter);
		
	}

}
