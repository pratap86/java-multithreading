package com.pratap.forkjoin;

import static com.pratap.util.CommonUtil.stopWatch;
import static com.pratap.util.CommonUtil.delay;
import static com.pratap.util.LoggerUtil.log;

import java.util.ArrayList;
import java.util.List;

import com.pratap.util.DataSet;

public class StringTransFormExample {

	public static void main(String[] args) {

		stopWatch.start();
		
		List<String> resultList = new ArrayList<>();
		
		DataSet.nameList().forEach(name -> {
			String newValue = addNameLengthTransform(name);
			resultList.add(newValue);
		});
		
		stopWatch.stop();
		log("Final result : "+ resultList);
		log("Total time tacken : "+stopWatch.getTime());
	}

	private static String addNameLengthTransform(String name) {
		delay(500);
		return name.length()+" - "+name;
	}

}
