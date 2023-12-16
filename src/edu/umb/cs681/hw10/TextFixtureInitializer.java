package edu.umb.cs681.hw10;

import java.time.LocalDateTime;

public class TextFixtureInitializer {
	
    static LocalDateTime Time = LocalDateTime.now();
	public static FileSystem createFs() {
		FileSystem fs = FileSystem.getFileSystem();
		
		Directory prjRoot = new Directory(null, "prjRoot", null);

		Directory src = new Directory(prjRoot, "src", Time);
		Directory lib = new Directory(prjRoot, "lib", Time);
		Directory test = new Directory(prjRoot, "test", Time);

		File x = new File(prjRoot, "x", 200, Time);

		File a = new File(src, "a", 150, Time);
		File b = new File(src, "b", 150, Time);
		
		File c = new File(lib, "c", 150, Time);

		Directory srcOfTest = new Directory(test, "src", Time);

		File d = new File(srcOfTest, "d", 100, Time);

		Link y = new Link(prjRoot, "y", Time, srcOfTest);

		fs.appendRootDir(prjRoot);
		return fs;
	}
}


