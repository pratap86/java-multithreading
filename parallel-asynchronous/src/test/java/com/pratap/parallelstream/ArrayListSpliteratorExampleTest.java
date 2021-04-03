package com.pratap.parallelstream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.RepeatedTest;

import com.pratap.util.DataSet;
import static com.pratap.util.CommonUtil.stopWatchReset;

class ArrayListSpliteratorExampleTest {
	
	private ArrayListSpliteratorExample arrayListSpliterator = new ArrayListSpliteratorExample();

	@RepeatedTest(5)
	void testMultiplyEachValueUsingArrayListWithSequentialStream() {
		stopWatchReset();
		int size = 1000000;
		List<Integer> inputList = DataSet.generateArrayList(size);
		
		List<Integer> resultList = arrayListSpliterator.multiplyEachValue(inputList, 2, false);
		
		assertEquals(1000000, resultList.size());
	}
	
	@RepeatedTest(5)
	void testMultiplyEachValueUsingArrayListWithParallerlStream() {
		stopWatchReset();
		int size = 1000000;
		List<Integer> inputList = DataSet.generateArrayList(size);
		
		List<Integer> resultList = arrayListSpliterator.multiplyEachValue(inputList, 2, true);
		
		assertEquals(1000000, resultList.size());
	}

}
