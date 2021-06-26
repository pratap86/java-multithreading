package com.pratap.multithreading.questions.interthreadcommunication.printnumbersbythreethreads;

/**
 * <table><tbody>
 * <tr>
 * 	<td>Thread1</td>
 * 	<td>Thread2</td>
 * 	<td>Thread3</td>
 * </tr>
 * <tr>
 * 	<td>1</td>
 * 	<td>2</td>
 * 	<td>3</td>
 * </tr>
 * <tr>
 * 	<td>4</td>
 * 	<td>5</td>
 * 	<td>6</td>
 * </tr>
 * <tr>
 * 	<td>7</td>
 * 	<td>8</td>
 * 	<td>9</td>
 * </tr>
 * <tr>
 * 	<td>10</td>
 * 	<td>..</td>
 * 	<td>..</td>
 * </tr>
 * <tr>
 * 	<td>%3=1</td>
 * 	<td>%3=2</td>
 * 	<td>%3=0</td>
 * </tr>
 * </tbody>
 * </table>
 * 
 * <blockquote><pre>
 * If number%3==1 then Thread1 will print the number and increment it else will go in the wait state.
 * If number%3==2 then Thread2 will print the number and increment it else will go in the wait state.
 * If number%3==0 then Thread3 will print the number and increment it else will go in the wait state.
 * </pre></blockquote>
 * @author Pratap Narayan
 *
 */
public class NumberGenerator {

	private int number = 1;
	private int numberOfThreads;
	private int totalNumbersInSequence;
	
	public NumberGenerator(int numberOfThreads, int totalNumbersInSequence) {
		this.numberOfThreads = numberOfThreads;
		this.totalNumbersInSequence = totalNumbersInSequence;
	}
	
	/**
	 * 
	 * @param remainder = number % 3
	 */
	public void printNumber(int remainder) {
		
		synchronized (this) {
			while (number < totalNumbersInSequence -1) {
				// only permits the particular thread prints the particular number in sequence
				// ie if remainder 1, then thread-1 going to print the numbers, remainder 2 -> thread-2, remainder 0 -> thread 3
				while (number % numberOfThreads != remainder) {
					
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println(Thread.currentThread().getName()+" "+number++);
				notifyAll();
			}
		}
	}
}
