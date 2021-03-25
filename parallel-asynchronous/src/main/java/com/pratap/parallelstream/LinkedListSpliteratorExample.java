package com.pratap.parallelstream;

import static com.pratap.util.CommonUtil.startTimer;
import static com.pratap.util.CommonUtil.timeTaken;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LinkedListSpliteratorExample {

public List<Integer> multiplyEachValue(LinkedList<Integer> inputList, int multiplyValue, boolean isParallel){
		
		startTimer();
		Stream<Integer> integerStream = inputList.stream();
		
		if(isParallel) integerStream.parallel();
		
		List<Integer> resultList = integerStream
				.map(integer -> integer*multiplyValue)
				.collect(Collectors.toList());
		
		timeTaken();
		return resultList;
				
	}
}
