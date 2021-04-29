package com.pratap.multithreading.questions.interthreadcommunication.printoddandevenbytwothreads;

public class Printer {
	
	private volatile boolean isOdd;

	synchronized void printEvenNum(int number) {
		
		while (!isOdd) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println(Thread.currentThread().getName() + ":" + number);
        isOdd = false;
        notify();
	}

	synchronized void printOddNum(int number) {

		while (isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + number);
        isOdd = true;
        notify();
	}

}
