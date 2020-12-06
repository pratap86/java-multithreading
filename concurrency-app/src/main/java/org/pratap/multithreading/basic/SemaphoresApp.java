package org.pratap.multithreading.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 
 * @author Pratap Narayan
 * 
 *         semaphores maintains a set of permits 
 *         - acquire() -> if permit is available than taken it 
 *         - release() -> adds a permit 
 *         -: semaphores
 *         just keeps a count of the number available - new Semaphore(int
 *         permits, boolean fairness)
 *
 */

enum Downloader {

	INSTANCE;
	
	private Semaphore semaphore = new Semaphore(5, true);
	
	public void downloadData() {
		
		try {
			semaphore.acquire();
			download();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

	private void download() {

		System.out.println("Downloading the data from web...");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

public class SemaphoresApp {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for( int i = 0; i < 20; i++ ) {
			
			executorService.execute(() -> {
				
				Downloader.INSTANCE.downloadData();
				
			});
			
		}
	}

}
