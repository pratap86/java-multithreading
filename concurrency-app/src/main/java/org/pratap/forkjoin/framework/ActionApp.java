package org.pratap.forkjoin.framework;

import java.util.concurrent.ForkJoinPool;

/**
 * 
 * @author Pratap Narayan
 * 
 * 1. fork - Asynchronous execute the given task in the poll, we can call this on RecursiveAction or RecursiveTask<T> 
 * 2. join -returns the result of the computation, when it is done
 * 3. invoke -execute the given task + return its result on completion.
 *
 */
public class ActionApp {

	public static void main(String[] args) {

		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		
		SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(101);
		
		pool.invoke(simpleRecursiveAction);
	}

}
