package com.pratap.multithreading.questions.interthreadcommunication.printnumbersbythreethreads;

public class SequenceGeneratorRunnable implements Runnable {

	private NumberGenerator numberGenerator;
	private int remainder;
	
	public SequenceGeneratorRunnable(NumberGenerator numberGenerator, int remainder) {
		this.numberGenerator = numberGenerator;
		this.remainder = remainder;
	}

	@Override
	public void run() {
		numberGenerator.printNumber(remainder);
	}

}
