package com.pratap.multithreading.fundamentals.thread.creation;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutorMain {

	public static void main(String[] args) {

		List<Runnable> tasks = new ArrayList<Runnable>();
		
		Runnable firstTask = () -> {
			System.out.println("task-01");
		};
		
		Runnable secondTask = () -> {
			System.out.println("task-02");
		};
		
		Runnable thirdTask = () -> {
			System.out.println("task-03");
		};
		
		tasks.add(firstTask);
		tasks.add(secondTask);
		tasks.add(thirdTask);
		
		MultiExecutor multiExecutor = new MultiExecutor(tasks);
		multiExecutor.executeAll();
	}

}
