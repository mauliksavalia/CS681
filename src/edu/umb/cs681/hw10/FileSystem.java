package edu.umb.cs681.hw10;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileSystem {
    private static AtomicReference<FileSystem> Instance = new AtomicReference<>();
    private static AtomicBoolean atomicFlag = new AtomicBoolean(false);
    private static LinkedList<Directory> rootDirs = new LinkedList<>();

    private FileSystem() {
        FileSystem.rootDirs = new LinkedList<Directory>();
    }

    public static FileSystem getFileSystem() {
        Instance.compareAndSet(null, new FileSystem());
        return Instance.get();
    }

    public LinkedList<Directory> getRootDirs() {
		return rootDirs;
	}

	public void appendRootDir(Directory root) {
		FileSystem.rootDirs.add(root);
	}

    public static void main(String[] args) {
        FileSystem fs = FileSystem.getFileSystem();
    
        Runnable fsAccess = () -> {
            while (!atomicFlag.get()) {
                System.out.println("Thread " + Thread.currentThread().getId() + " is accessing FileSystem");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    
        Thread[] threads = new Thread[15];
        for (int i = 0; i < 15; i++) {
            threads[i] = new Thread(fsAccess);
            threads[i].start();
        }
    
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        atomicFlag.set(true); // Signal all threads to terminate
    
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}