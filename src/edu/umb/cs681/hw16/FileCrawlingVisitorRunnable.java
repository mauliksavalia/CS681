package edu.umb.cs681.hw16;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileCrawlingVisitorRunnable implements Runnable {
    private Directory rootDir;
    private AtomicBoolean done = new AtomicBoolean(false);
    private static ThreadLocal<FileCrawlingVisitor> visitor = ThreadLocal.withInitial(() -> new FileCrawlingVisitor());
    private static ConcurrentLinkedQueue<File> sharedFileList = new ConcurrentLinkedQueue<>(); 

    private List<File> filesToAdd = new ArrayList<>();
    private List<File> addedFiles = new ArrayList<>();

    public FileCrawlingVisitorRunnable(Directory rootDir) {
        this.rootDir = rootDir;
    }

    public static ConcurrentLinkedQueue<File> getSharedFileList() { 
        return sharedFileList;
    }

    public void setDone() {
        done.set(true);
    }

    public void run() {
        while(true) {
            if (done.get()) {
                break;
            }
            rootDir.accept(visitor.get());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break; 
            }
        }

        for (File file : visitor.get().getFiles()) {
            if (!addedFiles.contains(file)) {
                filesToAdd.add(file);
                addedFiles.add(file);
            }
        }
        sharedFileList.addAll(filesToAdd); 
    }
}
