package edu.umb.cs681.hw12;

public class DepositRunnable implements Runnable {
    private ThreadSafeBankAccount2 account;
    private volatile boolean done = false;

    public DepositRunnable(ThreadSafeBankAccount2 account) {
        this.account = account;
    }

    public void setDone() {
        done = true;
    }

    public void run() {
        for(int i = 0; i < 12; i++) {
            if(done) {
                break;
            }
            account.deposit(150); 
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                continue;
            }
        }
    }
}