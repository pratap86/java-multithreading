package org.pratap.multithreading.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Producer-Consumer problem with half of low level synchronization
 * implemented with legacy approach ie wait & notify
 * 
 * @author 835698
 *
 */
public class ProducerConsumerLegacyApp {

	public static void main(String[] args) {

		ProcessorAgain processor = new ProcessorAgain();

		Thread t1 = new Thread(() -> {
			try {
				processor.produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				processor.consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();

//		try {
//			t1.join();
//			t2.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

}

class ProcessorAgain {

	private List<Integer> list = new ArrayList<Integer>();
	
	private final int LIMIT = 5;
	private final int BOTTOM = 0;
	
	private final Object lock = new Object();
	
	private int value = 0;
	
	// Producer is for adding new items into a list
	public void produce() throws InterruptedException {

		synchronized (lock) {
		
			while (true) {
				
				if( list.size() == LIMIT ) {
					
					System.out.println("waiting for removing items in to the list...");
					lock.wait();
					
				} else {
					
					System.out.println("Adding : "+value);
					list.add(value);
					value++;
					lock.notify();// notify to other thread in each iteration
					
				}
				
				Thread.sleep(500);
				
			}
			
		}
		
	}
	// Consumer, if a shared resource reaches to a certain threshold, is going to remove items from list
	public void consume() throws InterruptedException {

		synchronized (lock) {
		
			while (true) {
				
				if( list.size() == BOTTOM ) {
					
					System.out.println("waiting for adding item into the list...");
					lock.wait();
					
				} else {
					
					System.out.println("Removed : "+list.remove(--value));
					lock.notify();
					
				}
				
				Thread.sleep(500);
			}
			
		}
		
	}

}
