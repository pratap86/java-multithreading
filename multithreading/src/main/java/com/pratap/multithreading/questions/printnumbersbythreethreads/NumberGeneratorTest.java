package com.pratap.multithreading.questions.printnumbersbythreethreads;

public class NumberGeneratorTest {

    private static final int TOTAL_NO_IN_SEQUENCE = 10;
    private static final int TOTAL_NO_OF_THREADS = 3;

    public static void main(String[] args) {

        NumberGenerator numberGenerator = new NumberGenerator(TOTAL_NO_OF_THREADS, TOTAL_NO_IN_SEQUENCE);

        Thread t1 = new Thread(new SequenceGeneratorRunnable(numberGenerator, 1), "Thread-1");
        Thread t2 = new Thread(new SequenceGeneratorRunnable(numberGenerator, 2), "Thread-2");
        Thread t3 = new Thread(new SequenceGeneratorRunnable(numberGenerator, 0), "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
