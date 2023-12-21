package edu.umb.cs681.hw16;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileCrawlingVisitor implements FSVisitor {
    private LinkedList<File> files = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock();

    public void visit(Link link) {  
    }

    public void visit(Directory dir) {
    }

    public void visit(File file) {
        lock.lock();
        try {
            files.add(file);
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<File> getFiles() {
        lock.lock();
        try {
            return files;
        } finally {
            lock.unlock();
        }
    }
}

