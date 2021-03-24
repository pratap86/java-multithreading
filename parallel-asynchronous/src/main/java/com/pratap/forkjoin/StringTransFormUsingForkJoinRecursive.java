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

public class StringTransFormUsingForkJoinRecursive extends RecursiveTask<List<String>>{

	private static final long serialVersionUID = 6907759632193519531L;
	
	private List<String> inputList;
	
	public StringTransFormUsingForkJoinRecursive(List<String> inputList) {
		this.inputList = inputList;
	}

	public static void main(String[] args) {

		stopWatch.start();
		
		List<String> resultList = new ArrayList<>();
		
		List<String> names = DataSet.nameList();
		
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		StringTransFormUsingForkJoinRecursive forkJoinRecursive = new StringTransFormUsingForkJoinRecursive(names);
		resultList = forkJoinPool.invoke(forkJoinRecursive);//task added to the shared queue
		
		stopWatch.stop();
		log("Final result : "+ resultList);
		log("Total time tacken : "+stopWatch.getTime());
	}

	private static String addNameLengthTransform(String name) {
		delay(500);
		return name.length()+" - "+name;
	}

	@Override
	protected List<String> compute() {
		
		if(inputList.size() <= 1) {
			List<String> resultList = new ArrayList<>();
			inputList.forEach(name -> resultList.add(addNameLengthTransform(name)));
			return resultList;
		}

		int midpoint = inputList.size()/2;
		
		ForkJoinTask<List<String>> leftInputList =  new StringTransFormUsingForkJoinRecursive(inputList.subList(0, midpoint)).fork();
		inputList = inputList.subList(midpoint, inputList.size());
		List<String> rightResult = compute();// recursion happens
		
		List<String> leftResult = leftInputList.join();
		leftResult.addAll(rightResult);
		return leftResult;
	}

}
