package edu.umb.cs681.hw08;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
	private static FileSystem instance = null;
    private static LinkedList<Directory> rootDirs;
    private static ReentrantLock lock = new ReentrantLock();

    private FileSystem() {
        FileSystem.rootDirs = new LinkedList<Directory>();
    }

	public static FileSystem getFileSystem() {
		lock.lock();
		try {
			if (instance == null) {
				instance = new FileSystem();
			}
		}finally{
			lock.unlock();
		}
		return instance;
	}

	public LinkedList<Directory> getRootDirs() {
		return rootDirs;
	}

	public void appendRootDir(Directory root) {
		FileSystem.rootDirs.add(root);
	}

	public static void main(String[] args) throws InterruptedException {
		FileSystem fs = TestFixtureInitializer.createFs();

		// Test for 'src' Directory
		System.out.println("\nTest for 'src' Directory:");
		Runnable task1 = () -> {
			System.out.print("\nFor Thread 1, First child of 'prjRoot' Directory is: ");

			System.out.println(fs.getRootDirs().getFirst().getChildren().get(0).getName());
		};

		Runnable task2 = () -> {
			System.out.print("\nFor Thread 2, First child of 'prjRoot' Directory is: ");

			System.out.println(fs.getRootDirs().getFirst().getChildren().get(0).getName());
		};

		Thread thread1 = new Thread(task1);
		Thread thread2 = new Thread(task2);
   
		thread1.start();
		thread2.start();
        
		thread1.join();
		thread2.join();

        System.out.println("");
		System.out.print("First child of 'prjRoot' Directory is: ");
		System.out.println(fs.getRootDirs().getFirst().getChildren().get(0).getName());
		System.out.println("");
		System.out.println("\nTest for 'lib' Directory:");
		

		// Test for 'lib' Directory
		Runnable task3 = () -> {
			System.out.print("\nFor Thread 3, Second child of 'prjRoot' Directory is: ");

			System.out.println(fs.getRootDirs().getFirst().getChildren().get(2).getName());
		};

		Runnable task4 = () -> {
			System.out.print("\nFor Thread 4, Second child of 'prjRoot' Directory is: ");

			System.out.println(fs.getRootDirs().getFirst().getChildren().get(2).getName());
		};

		Thread thread3 = new Thread(task3);
		Thread thread4 = new Thread(task4);
   
		thread3.start();
		thread4.start();
        
		thread3.join();
		thread4.join();

        System.out.println("");
		System.out.print("Second child of 'prjRoot' Directory is: ");
		System.out.println(fs.getRootDirs().getFirst().getChildren().get(2).getName());
		System.out.println("");
		System.out.println("\nTest for 'test' Directory:");


		// Test for 'test' Directory
		Runnable task5 = () -> {
			System.out.print("\nFor Thread 5, Third child of 'prjRoot' Directory is: ");

			System.out.println(fs.getRootDirs().getFirst().getChildren().get(4).getName());
		};

		Runnable task6 = () -> {
			System.out.print("\nFor Thread 6, Third child of 'prjRoot' Directory is: ");

			System.out.println(fs.getRootDirs().getFirst().getChildren().get(4).getName());
		};

		Thread thread5 = new Thread(task5);
		Thread thread6 = new Thread(task6);
   
		thread5.start();
		thread6.start();
        
		thread5.join();
		thread6.join();

        System.out.println("");
		System.out.print("Third child of 'prjRoot' Directory is: ");
		System.out.println(fs.getRootDirs().getFirst().getChildren().get(4).getName());
		System.out.println("");
		System.out.println("\nTest for 'prjRoot' Directory:");


		// Test for 'prjRoot' Directory
		Runnable task7 = () -> {
			System.out.print("\nFor Thread 7, The Root Folder is: ");

			System.out.println(fs.getRootDirs().getFirst().getName());
		};

		Runnable task8 = () -> {
			System.out.print("\nFor Thread 8, The Root Folder is: ");

			System.out.println(fs.getRootDirs().getFirst().getName());
		};

		Thread thread7 = new Thread(task7);
		Thread thread8 = new Thread(task8);

		thread7.start();
		thread8.start();
        
		thread7.join();
		thread8.join();

        System.out.println("");
		System.out.print("The Root folder is: ");
		System.out.println(fs.getRootDirs().getFirst().getName());
	}
}