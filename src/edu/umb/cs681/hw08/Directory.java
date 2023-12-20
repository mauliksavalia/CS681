package edu.umb.cs681.hw08;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {
    LinkedList<FSElement> children;

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
        this.children.add(child);
    }

    public int countChildren() {
        return this.children.size();
    }

    public LinkedList<FSElement> getChildren() {
        return this.children;
    }

    public LinkedList<Directory> getSubDirectories() {
        
        LinkedList<Directory> subDir = new LinkedList<>();

        for (int j = 0; j < children.size(); j++) {
            if (children.get(j).isDirectory()) {
                subDir.add((Directory) children.get(j));
            }
        }
        return subDir;
    }

    public LinkedList<File> getFiles() {

        LinkedList<File> files = new LinkedList<>();

        for (int j = 0; j < this.children.size(); j++) {
            if (!children.get(j).isDirectory()) {
                files.add((File) children.get(j));
            }
        }
        return files;
    }

    public int getTotalSize() {

        int tSize = 0;

        for (int j = 0; j < this.children.size(); j++) {
            if (!this.children.get(j).isDirectory()) {
                tSize += this.children.get(j).size;
            } else {
                Directory dir = (Directory) this.children.get(j);
                tSize += dir.getTotalSize();
            }
        }
        return tSize;
    }

}
