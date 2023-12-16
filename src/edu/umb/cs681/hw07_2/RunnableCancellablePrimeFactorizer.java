package edu.umb.cs681.hw07_2;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
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
                if(done) {
                    System.out.println("Stopped generating prime Factors.");
                    this.factors.clear();
                    break;
                }
            
            if(divisor > 2 && isEven(divisor)) {
                divisor++;
                continue;
            }

            if(dividend % divisor == 0) {
                factors.add(divisor);
                dividend /= divisor;
            } else {
                if(divisor == 2) divisor++;
                else divisor += 2;
            }
            }finally{
                lock.unlock();
            }
        }
    }

    @Override
    public void run() {
        generatePrimeFactors();
        System.out.println("Thread #" + Thread.currentThread().threadId() + " generated " + factors);
    }
}
