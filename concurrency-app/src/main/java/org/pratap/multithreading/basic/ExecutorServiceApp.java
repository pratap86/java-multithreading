package org.pratap.multithreading.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author Pratap Narayan
 * 
 * 1. ExecutorService executorService = Executors.newCachedThreadPool();
 * 
 * 2. ExecutorService executorService = Executors.newFixedThreadPool(N);
 * 
 * 3. ExecutorService executorService = Executors.newSingleThreadExecutor();
 * 
 *  -- execute() -> Runnable only
 *  -- submit()  -> Runnable + Callable
 */
public class ExecutorServiceApp {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		for(int i = 0; i < 5; i++) {
			executorService.submit( () -> {
				
				for(int j = 0; j < 10; j++) {
					System.out.println(j);
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
		}
	}

}
