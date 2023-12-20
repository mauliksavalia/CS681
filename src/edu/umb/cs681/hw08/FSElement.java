package edu.umb.cs681.hw08;

import java.time.LocalDateTime;

public abstract class FSElement {
	protected Directory parent;
    protected String name;
    protected int size;
	protected LocalDateTime creationTime;

	public FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
		this.parent = parent;
		this.name = name;
		this.size = size;
		this.creationTime = creationTime;

		if (this.parent != null) {
			parent.appendChild(this);
		}
	}

	public Directory getParent() {
		return parent;
	}

	public void setParent(Directory parent) {
        this.parent = parent;
    }

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
        this.size = size;
    }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
        this.name = name;
    }
	
	public abstract boolean isDirectory();

    public LocalDateTime getCreationTime() {
        return creationTime;
    }	

	public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}

