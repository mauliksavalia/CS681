package edu.umb.cs681.hw12;

import java.util.concurrent.atomic.AtomicBoolean;

public class WithdrawRunnable implements Runnable {
    private ThreadSafeBankAccount2 account;
    private AtomicBoolean done = new AtomicBoolean(false);

    public WithdrawRunnable(ThreadSafeBankAccount2 account) {
        this.account = account;
    }

    public void setDone() {
        done.set(true);
    }

    public void run() {
        for(int i = 0; i < 10; i++) { // Assuming 10 iterations
            if(done.get()) {
                break;
            }
            account.withdraw(100);
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

