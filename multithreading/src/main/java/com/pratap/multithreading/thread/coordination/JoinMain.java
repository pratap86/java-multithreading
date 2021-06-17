package com.pratap.multithreading.thread.coordination;

import java.math.BigInteger;

public class JoinMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static class FactorialThread extends Thread {

		private Long inputNumber;
		private BigInteger result = BigInteger.ZERO;
		private boolean isFInished;

		public FactorialThread(Long inputNumber) {
			this.inputNumber = inputNumber;
		}

		@Override
		public void run() {
			this.result = factorial(inputNumber);
			this.isFInished = true;
		}

		private BigInteger factorial(Long number) {
			BigInteger tempResult = BigInteger.ONE;
			
			for(Long i = number; i > 0; i--) {
				tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
			}
			return tempResult;
		}

		public BigInteger getResult() {
			return result;
		}

		public boolean isFInished() {
			return isFInished;
		}

	}
}
