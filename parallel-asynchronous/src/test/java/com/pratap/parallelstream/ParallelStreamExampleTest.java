package com.pratap.parallelstream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.pratap.util.CommonUtil.startTimer;
import static com.pratap.util.CommonUtil.timeTaken;
import static com.pratap.util.CommonUtil.stopWatchReset;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.pratap.util.DataSet;

class ParallelStreamExampleTest {

	private ParallelStreamExample parallelStreamExample = new ParallelStreamExample();
	
	@Test
	void testStringTransform() {

		List<String> nameList = DataSet.nameList();
		startTimer();
		List<String> resultList = parallelStreamExample.stringTransform(nameList);
		timeTaken();
		assertEquals(4, resultList.size());
		resultList.forEach(name -> assertTrue(name.contains("-")));
	}
	
	@ParameterizedTest
	@ValueSource(booleans = {false, true})
	void testStringTransformWithParallelAndSequentialStream(boolean isParallel) {

		List<String> nameList = DataSet.nameList();
		stopWatchReset();
		startTimer();
		List<String> resultList = parallelStreamExample.stringTransform(nameList, isParallel);
		timeTaken();
		
		assertEquals(4, resultList.size());
		resultList.forEach(name -> assertTrue(name.contains("-")));
	}
	
	@ParameterizedTest
	@ValueSource(booleans = {false, true})
	void teststringTransformToLowerCaseWithParallelAndSequentialStream(boolean isParallel) {

		List<String> nameList = DataSet.nameList();
		List<String> resultList = parallelStreamExample.stringTransformToLowerCase(nameList, isParallel);
		
		assertEquals(4, resultList.size());
		assertEquals("pratap", resultList.get(0));
	}
	
	
}
