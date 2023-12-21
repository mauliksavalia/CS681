package edu.umb.cs681.hw11;

import java.util.LinkedList;

public class Main {
    
    public static void main(String[] args) {

        Directory root1 = TextFixtureInitializer.createTreeOne();
        Directory root2 = TextFixtureInitializer.createTreeTwo();
        Directory root3 = TextFixtureInitializer.createTreeThree();

        FileCrawlingVisitorRunnable crawler1 = new FileCrawlingVisitorRunnable(root1);
        FileCrawlingVisitorRunnable crawler2 = new FileCrawlingVisitorRunnable(root2);
        FileCrawlingVisitorRunnable crawler3 = new FileCrawlingVisitorRunnable(root3);
    
        Thread thread1 = new Thread(crawler1);
        Thread thread2 = new Thread(crawler2);
        Thread thread3 = new Thread(crawler3);
    
        thread1.start();
        thread2.start();
        thread3.start();
    
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        crawler1.setDone();
        crawler2.setDone();
        crawler3.setDone();

        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();
    
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LinkedList<File> allFiles = FileCrawlingVisitorRunnable.getSharedFileList();
        System.out.println("All identified files in Shared List:");

        allFiles.forEach(file -> 
                System.out.println(file.getName()));
    }
}

