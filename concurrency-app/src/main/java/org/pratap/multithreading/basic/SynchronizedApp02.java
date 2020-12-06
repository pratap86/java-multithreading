package org.pratap.multithreading.basic;
/**
 * 
 * @author 835698
 * @synchronized used with method, uses the class intrinsic lock, same with synchronized(sameclass) - block
 * intrinsic lock - simultaneously no two threads can access the two different methods on same object
 * 
 */
public class SynchronizedApp02 {

	private static int count1 = 0;
	private static int count2 = 0;
	
	private static Object lock1 = new Object();
	private static Object lock2 = new Object();

	// below both statement are support intrinsic lock
//	private synchronized static void add() {
//		count1++;
//	}
	
//	private synchronized static void add() {
//		synchronized (SynchronizedApp02.class) {
//			count1++;
//		}
//	}

	
	private static void add() {
		// totally independent to each other, very fast
		synchronized (lock1) {
			++count1;
		}
	}

	private static void addAgain() {
		synchronized (lock2) {
			count2++;
		}
	}

	public static void compute() {
		for (int i = 0; i < 100; i++) {
			add();
			addAgain();
		}
	}

	public static void main(String[] args) {

		Thread t1 = new Thread(() -> {
			compute();
		});
		
		Thread t2 = new Thread(() -> {
			compute();
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("count1 = "+count1+" - count2 = "+count2);

	}

}
