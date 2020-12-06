package org.pratap.studentlibrary.simulation;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {

	private int id;
	private Lock lock;
	
	public Book(int id) {
		this.id = id;
		this.lock = new ReentrantLock();
	}
	
	public void read(Student student) throws InterruptedException {
		
		lock.lock();
		System.out.println(student+" start reading this book "+this);
		Thread.sleep(2000);
		lock.unlock();
		System.out.println(student+" has finished reading this book "+this);
		
	}

	@Override
	public String toString() {
		return " Book # "+id;
	}
	
	
	
}
