package com.pratap.multithreading.fundamentals.thread.creation;
/**
 * Thread Creation - MultiExecutor
 * @author Pratap Narayan
 *
 */

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

	private final List<Runnable> tasks;

	/*
     * @param tasks to executed concurrently
     */
	public MultiExecutor(List<Runnable> tasks) {
		this.tasks = tasks;
	}
	
    /**
     * Executes all the tasks concurrently
     */
	public void executeAll() {
		List<Thread> threads = new ArrayList<>(tasks.size());
		
		tasks.forEach((Runnable task) -> {
			Thread thread = new Thread(task);
			threads.add(thread);
		});
		
		threads.forEach((Thread thread) -> thread.start());
	}
	
	
}
