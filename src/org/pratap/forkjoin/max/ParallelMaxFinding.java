package org.pratap.forkjoin.max;

import java.util.concurrent.RecursiveTask;
/**
 * used only & only when we dealing with huge data set, for small to medium data collection sequential approach is better
 * @author Pratap Narayan
 *
 */
public class ParallelMaxFinding extends RecursiveTask<Integer> {

	private static final long serialVersionUID = -8624642228663144630L;

	private int[] nums;
	private int lowIndex;
	private int highIndex;

	public ParallelMaxFinding(int[] nums, int lowIndex, int highIndex) {
		this.nums = nums;
		this.lowIndex = lowIndex;
		this.highIndex = highIndex;
	}

	@Override
	protected Integer compute() {

		if (highIndex - lowIndex < App.THRESHOLD) {
			
			return sequentialMaxFind();
			
		} else {
			
			int middleIndex = (lowIndex + highIndex)/2;
			
			ParallelMaxFinding task1 = new ParallelMaxFinding(nums, lowIndex, middleIndex);
			ParallelMaxFinding task2 = new ParallelMaxFinding(nums, middleIndex+1, highIndex);
			
			invokeAll(task1, task2);
			
			return Math.max(task1.join(), task2.join());
			
		}

	}

	private Integer sequentialMaxFind() {

		int max = nums[lowIndex];

		for (int i = lowIndex+1; i < highIndex; i++) {

			if (nums[i] > max)
				max = nums[i];
		}

		return max;
	}

}
