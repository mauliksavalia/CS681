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
            }else { 
                if(divisor == 2) divisor++;
                else divisor += 2; }
            }finally{
                lock.unlock();
            }
        }
    }
    
    public static void main(String[] args) {
        RunnableCancellablePrimeFactorizer factorizer = new RunnableCancellablePrimeFactorizer(500, 2, (long)Math.sqrt(500));
        Thread thread = new Thread(factorizer);
        thread.start();
        try {
            Thread.sleep(100); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        factorizer.setDone();
        try {
            thread.join(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        factorizer.getPrimeFactors().forEach((Long factor) -> System.out.print(factor + ", "));
        System.out.println("\n" + factorizer.getPrimeFactors().size() + " prime factors are found.");
    }
}
