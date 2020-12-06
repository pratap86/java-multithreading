package org.pratap.multithreading.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * @author Pratap Narayan
 *
 */
class CallProcessor implements Callable<String> {

	private int id;
	
	public CallProcessor(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		return "id "+id;
	}
	
}
public class CallableFutureApp {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		List<Future<String>> list = new ArrayList<Future<String>>();
		
		for(int i = 0; i<5; i++) {
			
			Future<String> future = executorService.submit(new CallProcessor(i+1));
			
			list.add(future);
		}
		
		for(Future<String> future : list) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
