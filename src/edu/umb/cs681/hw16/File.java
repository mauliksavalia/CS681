package edu.umb.cs681.hw16;

import java.time.LocalDateTime;

public class File extends FSElement {
    public File(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
    }

    public boolean isDirectory() {
        return false;
    }

    public boolean isFile() {
        return true;
    }

    public String getName() {
        return name;
    }

    public void accept(FSVisitor v) {
        lock.lock();
        try{
		    v.visit(this);
        }finally{
            lock.unlock();
        }
	}
}

