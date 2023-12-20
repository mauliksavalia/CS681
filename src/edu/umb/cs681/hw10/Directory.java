package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {
    private LinkedList<FSElement> children;

    public Directory(Directory parent, String name, LocalDateTime creationTime) {
        super(parent, name, 0, creationTime);
        if (parent != null){
            parent.appendChild(parent);
        }
        this.children = new LinkedList<FSElement>();
    }

    public boolean isDirectory() {
        return true;
    }

    public void appendChild(FSElement child) {
        lock.lock();
        try {
            this.children.add(child);
        }finally{
            lock.unlock();
        }
    }

    public int countChildren() {
        lock.lock();
        try {
            return this.children.size();
        }finally{
            lock.unlock();
        }
    }

    public LinkedList<FSElement> getChildren() {
        lock.lock();
        try {
            return this.children; 
        }finally{
            lock.unlock();
        }
    }

    public LinkedList<Directory> getSubDirectories() {
        lock.lock();
        try {
            LinkedList<Directory> subDir = new LinkedList<>();
            for (FSElement child : children) {
                if (child.isDirectory()) {
                    subDir.add((Directory) child);
                }
            }
            return subDir;
        }finally{
            lock.unlock();
        }
    }

    public LinkedList<File> getFiles() {
        lock.lock();
        try {
            LinkedList<File> files = new LinkedList<>();
            for (FSElement child : children) {
                if (!child.isDirectory()) {
                    files.add((File) child);
                }
            }
            return files;
        }finally{
            lock.unlock();
        }
    }

    public int getTotalSize() {
        lock.lock();
        try {
            int tSize = 0;
            for (FSElement child : children) {
                if (!child.isDirectory()) {
                    tSize += child.getSize();
                } else {
                    Directory dir = (Directory) child;
                    tSize += dir.getTotalSize();
                }
            }
            return tSize;
        }finally{
            lock.unlock();
        }
    }
}
