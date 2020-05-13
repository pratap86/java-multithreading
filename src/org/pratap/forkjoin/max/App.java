package org.pratap.forkjoin.max;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

	public static int THRESHOLD = 0;
	
	public static void main(String[] args) {

		int[] nums = initializeNums();
		
		THRESHOLD = nums.length / Runtime.getRuntime().availableProcessors();
		
		SequentialMaxFinding sequentialMaxFinding = new SequentialMaxFinding();
		
		long start = System.currentTimeMillis();
		
		System.out.println(" Max : "+sequentialMaxFinding.sequentialMaxFind(nums, nums.length));
		System.out.println(" Time taken by Seqential Approach : "+(System.currentTimeMillis() - start)+" ms ");
		
		System.out.println("-------------------------------");
		
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		ParallelMaxFinding parallelMaxFinding = new ParallelMaxFinding(nums, 0, nums.length);
		
		start = System.currentTimeMillis();
		
		System.out.println(" Max: "+pool.invoke(parallelMaxFinding));
		System.out.println(" Time taken by Parallel Approach: "+(System.currentTimeMillis() - start)+" ms ");
		
	}

	private static int[] initializeNums() {

		Random random = new Random();
		
		int[] nums = new int[300000000];
		
		for(int i = 0; i < 300000000; i++) {
			nums[i] = random.nextInt(1000);
		}
		return nums;
	}

}
