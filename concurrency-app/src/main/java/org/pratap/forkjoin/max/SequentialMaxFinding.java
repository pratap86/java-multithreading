package org.pratap.forkjoin.max;

/**
 * For small to medium data collection, for large data collection prefer parallel approach
 * @author Pratap Narayan
 *
 */
public class SequentialMaxFinding {

	
	//O(n)
	public int sequentialMaxFind(int[] nums, int highIndex) {
		
		int max = nums[0];
		
		for(int i = 1; i < highIndex; i++) {
			
			if( nums[i] > max )
				max = nums[i];
		}
		
		return max;
		
	}
}
