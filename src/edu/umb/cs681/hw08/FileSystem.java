package edu.umb.cs681.hw08;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
	private static FileSystem Instance = null;
    private static LinkedList<Directory> rootDirs;
    private static ReentrantLock lock = new ReentrantLock();

    private FileSystem() {
        FileSystem.rootDirs = new LinkedList<Directory>();
    }

	public static FileSystem getFileSystem() {
		lock.lock();
		try {
			if (Instance == null) {
				Instance = new FileSystem();
			}
		} finally {
			lock.unlock();
		}

		return Instance;
	}

	public LinkedList<Directory> getRootDirs() {
		return rootDirs;
	}

	public void appendRootDir(Directory root) {
		FileSystem.rootDirs.add(root);
	}

	public static void main(String[] args) throws InterruptedException {
		FileSystem fs = TestFixtureInitializer.createFs();

		Runnable task1 = () -> {
			System.out.print("\nFor Thread 1, Root Folder is: ");

			System.out.println(fs.getRootDirs().getFirst().getName());
		};

		Runnable task2 = () -> {
			System.out.print("\nFor Thread 2, Root Folder is: ");

			System.out.println(fs.getRootDirs().getFirst().getName());
		};

        Runnable task3 = () -> {
			System.out.print("\nFor Thread 3, Root Folder is: ");

			System.out.println(fs.getRootDirs().getFirst().getName());
		};


		Thread thread1 = new Thread(task1);
		Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

		thread1.start();
		thread2.start();
        thread3.start();
        
		thread1.join();
		thread2.join();
        thread3.join();


        System.out.println("");
		System.out.print("The Root folder is: ");
		System.out.println(fs.getRootDirs().getFirst().getName());


		Runnable task4 = () -> {
			System.out.print("\nFor Thread 4, First child of Root Directory is: ");

			System.out.println(fs.getRootDirs().getFirst().getChildren().get(0).getName());
		};

		Runnable task5 = () -> {
			System.out.print("\nFor Thread 5, First child of Root Directory is: ");

			System.out.println(fs.getRootDirs().getFirst().getChildren().get(0).getName());
		};

        Runnable task6 = () -> {
			System.out.print("\nFor Thread 6, First child of Root Directory is: ");

			System.out.println(fs.getRootDirs().getFirst().getChildren().get(0).getName());
		};


		Thread thread4 = new Thread(task4);
		Thread thread5 = new Thread(task5);
        Thread thread6 = new Thread(task6);

		thread4.start();
		thread5.start();
        thread6.start();
        
		thread4.join();
		thread5.join();
        thread6.join();


        System.out.println("");
		System.out.print("First child of Root Directory is: ");
		System.out.println(fs.getRootDirs().getFirst().getChildren().get(0).getName());
	}
}