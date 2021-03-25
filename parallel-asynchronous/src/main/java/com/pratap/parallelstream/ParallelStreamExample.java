package com.pratap.parallelstream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.pratap.util.CommonUtil.delay;
import static com.pratap.util.CommonUtil.startTimer;
import static com.pratap.util.CommonUtil.timeTaken;
import static com.pratap.util.LoggerUtil.log;

import com.pratap.util.DataSet;

public class ParallelStreamExample {

	public static void main(String[] args) {

		List<String> nameList = DataSet.nameList();
		
		startTimer();
		List<String> resultList = new ParallelStreamExample().stringTransform(nameList);
		log("resultList : "+resultList);
		timeTaken();
	}

	public List<String> stringTransform(List<String> nameList) {

		return nameList
//				.stream()
				.parallelStream()
				.map(this::addNameLengthTransform)
				.sequential()
				.parallel()
				.collect(Collectors.toList());
	}
	
	public List<String> stringTransform(List<String> nameList, boolean isParallel) {

		Stream<String> nameStream = nameList.stream();
		if(isParallel)
			nameStream.parallel();
		
		return nameStream
				.map(this::addNameLengthTransform)
				.collect(Collectors.toList());
	}
	
	private String addNameLengthTransform(String name) {
		delay(500);
		return name.length()+" - "+name;
	}
	
	public List<String> stringTransformToLowerCase(List<String> nameList, boolean isParallel){
		
		Stream<String> nameStream = nameList.stream();
		if(isParallel)
			nameStream.parallel();
		return nameStream.map(String::toLowerCase).collect(Collectors.toList());
	}

}
