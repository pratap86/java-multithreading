package com.pratap.parallelstream;

import static com.pratap.util.CommonUtil.stopWatchReset;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.RepeatedTest;

import com.pratap.util.DataSet;

class LinkedListSpliteratorExampleTest {
	
	private LinkedListSpliteratorExample linkedListSpliterator = new LinkedListSpliteratorExample();

	@RepeatedTest(5)
	void testMultiplyEachValueUsingLinkedListWithParallelStream() {

		stopWatchReset();
		int size = 1000000;
		LinkedList<Integer> inputList = DataSet.generateIntegerLinkedList(size);
		
		List<Integer> resultList = linkedListSpliterator.multiplyEachValue(inputList, 2, true);
		
		assertEquals(1000000, resultList.size());
	}
	
	@RepeatedTest(5)
	void testMultiplyEachValueUsingLinkedListWithSequentialStream() {

		stopWatchReset();
		int size = 1000000;
		LinkedList<Integer> inputList = DataSet.generateIntegerLinkedList(size);
		
		List<Integer> resultList = linkedListSpliterator.multiplyEachValue(inputList, 2, false);
		
		assertEquals(1000000, resultList.size());
	}

}
