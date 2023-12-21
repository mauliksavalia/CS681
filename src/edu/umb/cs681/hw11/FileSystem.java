package edu.umb.cs681.hw11;

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

    public static LinkedList<Directory> getRootDirs() {
		instance.updateAndGet(fs -> fs);
        return FileSystem.rootDirs;
	}

	public void appendRootDir(Directory root) {
		instance.updateAndGet(fs -> {
            FileSystem.rootDirs.add(root);
            return fs;
        });
	}
}