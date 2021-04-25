package com.pratap.multithreading.questions.printnumbersbythreethreads;

public class SequenceGeneratorRunnable implements Runnable {

	private NumberGenerator numberGenerator;
	private int result;
	
	public SequenceGeneratorRunnable(NumberGenerator numberGenerator, int result) {
		this.numberGenerator = numberGenerator;
		this.result = result;
	}

	@Override
	public void run() {
		numberGenerator.printNumber(result);
	}

}
