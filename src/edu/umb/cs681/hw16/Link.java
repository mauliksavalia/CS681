package edu.umb.cs681.hw16;

import java.time.LocalDateTime;

public class Link extends FSElement {
    private FSElement target;

    public Link(Directory parent, String name, LocalDateTime creationTime, FSElement target) {
        super(parent, name, 0, creationTime);
        this.target = target;
    }

    public FSElement getTarget() {
        lock.lock();
        try {
            return target;
        } finally {
            lock.unlock();
        }
    }

    public boolean isDirectory() {
        return false;
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
