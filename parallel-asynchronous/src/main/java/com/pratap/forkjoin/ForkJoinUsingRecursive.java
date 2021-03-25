package com.pratap.forkjoin;

import static com.pratap.util.CommonUtil.stopWatch;
import static com.pratap.util.CommonUtil.delay;
import static com.pratap.util.LoggerUtil.log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import com.pratap.util.DataSet;
/**
 * Transform String through ForkJoin, improve the performance
 * @author Pratap Narayan
 *
 */
public class ForkJoinUsingRecursive extends RecursiveTask<List<String>>{

	private static final long serialVersionUID = 6907759632193519531L;
	
	private List<String> inputList;
	
	public ForkJoinUsingRecursive(List<String> inputList) {
		this.inputList = inputList;
	}

	public static void main(String[] args) {

		stopWatch.start();
		
		List<String> resultList = new ArrayList<>();
		
		List<String> names = DataSet.nameList();
		
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinUsingRecursive forkJoinRecursive = new ForkJoinUsingRecursive(names);
		resultList = forkJoinPool.invoke(forkJoinRecursive);//task added to the shared queue
		
		stopWatch.stop();
		log("Final result : "+ resultList);
		log("Total time tacken : "+stopWatch.getTime());
	}

	private static String addNameLengthTransform(String name) {
		delay(500);
		return name.length()+" - "+name;
	}

	/**
	 * Actual Fork & Join happens here, ie
	 * <blockquote>
	 * 1. split the data in to least possible small chunks<br>
	 * 2. process them separatly<br>
	 * 3. join them<br>
	 * 4. return the joined result to the caller
	 * </blockquote>
	 */
	@Override
	protected List<String> compute() {
		//condition for break the recursion
		if(inputList.size() <= 1) {
			List<String> resultList = new ArrayList<>();
			inputList.forEach(name -> resultList.add(addNameLengthTransform(name)));
			return resultList;
		}

		int midpoint = inputList.size()/2;
		
		ForkJoinTask<List<String>> leftInputList =  new ForkJoinUsingRecursive(inputList.subList(0, midpoint)).fork();
		//update the inputList, ie other side of list from midpoint to inputList size
		inputList = inputList.subList(midpoint, inputList.size());
		List<String> rightResult = compute();// recursion happens
		
		List<String> leftResult = leftInputList.join();
		leftResult.addAll(rightResult);
		return leftResult;
	}

}
