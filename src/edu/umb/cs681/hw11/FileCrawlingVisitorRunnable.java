package edu.umb.cs681.hw11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileCrawlingVisitorRunnable implements Runnable {
    private Directory rootDir;
    private AtomicBoolean done = new AtomicBoolean(false);
    private static ThreadLocal<FileCrawlingVisitor> visitor = ThreadLocal.withInitial(() -> new FileCrawlingVisitor());
    private static LinkedList<File> sharedFileList = new LinkedList<>();

    private List<File> filesToAdd = new ArrayList<>();
    private List<File> addedFiles = new ArrayList<>();

    public FileCrawlingVisitorRunnable(Directory rootDir) {
        this.rootDir = rootDir;
    }

    public static LinkedList<File> getSharedFileList() {
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
        synchronized (sharedFileList) {
            sharedFileList.addAll(filesToAdd); 
        }
    }
}
