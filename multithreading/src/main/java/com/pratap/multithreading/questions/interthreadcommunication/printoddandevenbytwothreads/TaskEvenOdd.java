package com.pratap.multithreading.questions.interthreadcommunication.printoddandevenbytwothreads;

public class TaskEvenOdd implements Runnable {

	private int max;// max is the range of odd & even numbers ie. odd & even number in between 10.
	private Printer printer;
	private boolean isEvenNumber;
	
	public TaskEvenOdd(int max, Printer printer, boolean isEvenNumber) {
		this.max = max;
		this.printer = printer;
		this.isEvenNumber = isEvenNumber;
	}

	@Override
	public void run() {

		int number = isEvenNumber ? 2 : 1;
		while (number <= max) {
			if (isEvenNumber) {
				printer.printEvenNum(number);
			} else {
				printer.printOddNum(number);
			}
			number += 2;
		}
	}


	public int getMax() {
		return max;
	}


	public Printer getPrinter() {
		return printer;
	}


	public boolean isEvenNumber() {
		return isEvenNumber;
	}

}
