package com.pratap.multithreading.thread.coordination;

import java.math.BigInteger;

public class ThreadIsInterruptedMain {

	public static void main(String[] args) {

		Thread thread = new Thread(new LongComputationTask(new BigInteger("2000000"), new BigInteger("100000000")));
		thread.start();
		thread.interrupt();
		
	}
	
	private static class LongComputationTask implements Runnable{
		
		private BigInteger base;
		private BigInteger power;
		
		public LongComputationTask(BigInteger base, BigInteger power) {
			this.base = base;
			this.power = power;
		}

		@Override
		public void run() {

			System.out.println(base+"^"+power+" = "+pow(base, power));
		}

		/**
		 * calculate the result of x^y, x is base & y is the power
		 * @param base
		 * @param power
		 * @return BigInteger as result
		 */
		private BigInteger pow(BigInteger base, BigInteger power) {
			BigInteger result = BigInteger.ONE;
			
			for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
				if(Thread.currentThread().isInterrupted()) {
					System.out.println("Prematuraly interrupted computation");
					return BigInteger.ZERO;
				}
				result = result.multiply(base);
			}
			return result;
		}
	}

}
