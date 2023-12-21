package edu.umb.cs681.hw11;

import java.time.LocalDateTime;

public class TextFixtureInitializer {
	
    static LocalDateTime Time = LocalDateTime.now();
	
    public static Directory createTreeOne() {
        Directory root1 = new Directory(null, "root1", Time);
        Directory dirX = new Directory(root1, "dirX", Time);
        File file1 = new File(dirX, "file1", 1000, Time);
        File file2 = new File(dirX, "file2", 500, Time);
        dirX.appendChild(file1);
        dirX.appendChild(file2);
        root1.appendChild(dirX);
        return root1;
    }

    public static Directory createTreeTwo() {
        Directory root2 = new Directory(null, "root2", Time);
        Directory dirY = new Directory(root2, "dirY", Time);
        File file3 = new File(dirY, "file3", 1200, Time);
        File file4 = new File(dirY, "file4", 2100, Time);
        dirY.appendChild(file3);
        dirY.appendChild(file4);
        root2.appendChild(dirY);
        return root2;
    }

    public static Directory createTreeThree() {
        Directory root3 = new Directory(null, "root3", Time);
        Directory dirZ = new Directory(root3, "dirZ", Time);
        File file5 = new File(dirZ, "file5", 900, Time);
        File file6 = new File(dirZ, "file6", 1100, Time);
        dirZ.appendChild(file5);
        dirZ.appendChild(file6);
        root3.appendChild(dirZ);
        return root3;
    }
}


