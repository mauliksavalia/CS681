package edu.umb.cs681.hw10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public class FileSystem {
    private static AtomicReference<FileSystem> instance = new AtomicReference<>();
    private static LinkedList<Directory> rootDirs;

    private FileSystem() {
        FileSystem.rootDirs = new LinkedList<Directory>();
    }

    public static FileSystem getFileSystem() {
        instance.compareAndSet(null, new FileSystem());
        return instance.get();
    }

    public LinkedList<Directory> getRootDirs() {
		instance.updateAndGet(fs -> fs);
        return FileSystem.rootDirs;
	}

	public void appendRootDir(Directory root) {
		instance.updateAndGet(fs -> {
            FileSystem.rootDirs.add(root);
            return fs;
        });
	}

    public static void main(String[] args) {
        FileSystem fs = FileSystem.getFileSystem();
        ArrayList<Thread> threads = new ArrayList<>();
        ArrayList<FileSystemRunnable> runnables = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            FileSystemRunnable runnable = new FileSystemRunnable(fs);
            Thread t = new Thread(runnable);
            threads.add(t);
            runnables.add(runnable);
            t.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 15; i++) {
            runnables.get(i).setDone();
            threads.get(i).interrupt();
        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
