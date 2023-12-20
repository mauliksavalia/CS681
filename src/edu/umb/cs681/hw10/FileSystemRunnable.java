package edu.umb.cs681.hw10;

import java.util.concurrent.atomic.AtomicBoolean;

public class FileSystemRunnable implements Runnable {
    private AtomicBoolean done = new AtomicBoolean(false);
    
    public FileSystemRunnable(FileSystem fs) {
    }

    public void setDone() {
        done.set(true);
    }

    public void run() {
        while(true) {
            if (done.get()) {
                break;
            }
            System.out.println("Thread " + Thread.currentThread().threadId() + " is accessing FileSystem");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                continue;
            }
        }
        System.out.println("Thread " + Thread.currentThread().threadId() + " is terminating");
    }
}
