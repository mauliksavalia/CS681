package edu.umb.cs681.hw09;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        }finally{
            lock.unlock();
        }
    }

    @Override
    public void generatePrimeFactors() {
        long divisor = from;
        while(dividend != 1 && divisor <= to) {
            lock.lock();
            try {
                if (done || Thread.interrupted()) {
                    System.out.println("Stopped generating prime factors.");
                    this.factors.clear();
                    return;
                }

                if (divisor > 2 && isEven(divisor)) {
                    divisor++;
                    continue;
                }

                if (dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                } else {
                    if (divisor == 2) divisor++;
                    else divisor += 2;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    @Override
    public void run() {
        generatePrimeFactors();
        System.out.println("Thread #" + Thread.currentThread().threadId() + " generated " + factors);
    }

    public static void main(String[] args) {
        // Create an instance of RunnableCancellableInterruptiblePrimeFactorizer
        RunnableCancellableInterruptiblePrimeFactorizer factorizer1 = 
            new RunnableCancellableInterruptiblePrimeFactorizer(105, 2, (long)Math.sqrt(105));

        // Start the thread
        Thread thread1 = new Thread(factorizer1);
        System.out.println("Starting thread to generate prime factors of 105");
        thread1.start();

        // Let the factorization run for a short period
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop the factorization thread
        factorizer1.setDone();

        // Interrupt the thread
        thread1.interrupt();

        try {
            thread1.join(); // Wait for the thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Prime factors: " + factorizer1.getPrimeFactors());

         RunnableCancellableInterruptiblePrimeFactorizer factorizer = 
            new RunnableCancellableInterruptiblePrimeFactorizer(30, 2, (long)Math.sqrt(30));

        Thread thread = new Thread(factorizer);
        System.out.println("Starting thread to generate prime factors of " + 30);
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        factorizer.setDone();
        thread.interrupt();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LinkedList<Long> actualFactors = factorizer.getPrimeFactors();
        System.out.println("Prime factors of " + 30 + ": " + actualFactors);
    }
}
