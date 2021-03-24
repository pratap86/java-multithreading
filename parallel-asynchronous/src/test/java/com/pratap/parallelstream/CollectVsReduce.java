package com.pratap.parallelstream;

import static com.pratap.util.LoggerUtil.log;

import java.util.stream.Collectors;

import com.pratap.util.DataSet;

public class CollectVsReduce {
	// collect return mutable object
	public static String collect() {
		
//		System.out.println(DataSet.nameList().stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
		return DataSet.nameList()
				.stream()
				.collect(Collectors.joining());
	}
	
	// reduce return immutable objects
	public static String reduce() {
		return DataSet.nameList()
				.stream()
				.reduce("", (s1, s2) -> s1 + s2);// created tons of immutable objects
	}

	public static void main(String[] args) {

		log("collect : "+collect());
		log("reduce : "+reduce());
	}

}
