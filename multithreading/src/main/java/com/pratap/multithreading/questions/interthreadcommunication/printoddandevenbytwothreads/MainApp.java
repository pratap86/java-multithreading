package com.pratap.multithreading.questions.interthreadcommunication.printoddandevenbytwothreads;

public class MainApp {

	public static void main(String[] args) {

		Printer printer = new Printer();
		
		Thread t1 = new Thread(new TaskEvenOdd(10, printer, false), "odd");
		Thread t2 = new Thread(new TaskEvenOdd(10, printer, true), "even");
		
		t1.start();
		t2.start();
	}

}
