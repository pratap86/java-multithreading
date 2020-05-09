package org.pratap.concurrent.collections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

///**
//* This is used to synchronize one or more tasks by forcing them to wait for the completion of a set of operations being performed by other tasks
//* 
//* 		- You give an initial count to a CountDownLatch object, and any task that calls await()
//* 				on that object will block until the count reaches zero
//* 
//* 		- Other tasks may call countDown() on the object to reduce the count, presumably when a task finishes its job
//* 		- A CountDownLatch is designed to be used in a one-shot fashion; the count cannot be reset !!!
//* 				If you need a version that resets the count, you can use a CyclicBarrier instead
//* 
//* 		- The tasks that call countDown() are not blocked when they make that call. Only the call to await() is blocked until the count reaches zero
//* 
//* 		A typical use is to divide a problem into N independently solvable tasks and create a CountDownLatch with a value of N.
//* 		When each task is finished it calls countDown() on the latch. Tasks waiting for the problem to be solved call await() 
//* 		on the latch to hold themselves back until it is completed


//*			For example : you want to trigger something after 10, 000 users download an image.
//*
//*/

class Worker implements Runnable {

	private int id;
	
	private CountDownLatch countDownLatch;
	
	public Worker(int id, CountDownLatch countDownLatch) {
		this.id = id;
		this.countDownLatch = countDownLatch;
	}
	
	@Override
	public void run() {

		doWork();
		countDownLatch.countDown();// its going to decreases the count value by 1 in each iteration.
	}

	private void doWork() {

		System.out.println("Thread with id : "+this.id+" start working...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

public class CountDownLatches {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		CountDownLatch latch = new CountDownLatch(5);
		
		for(int i = 1; i <= 5; i++) {
			executorService.execute(new Worker(i, latch));
		}
		try {
			latch.await();// every single worker thread has to finished their job
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All the prerequisites are done..");
		executorService.shutdown();
	}

}
