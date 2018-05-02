/**
 * 
 */
package test;

import java.util.*;
import java.io.*;

/**
 * 
 * @author 分裂状态。
 *
 */
public class FileTest {
	public static void main(String[] args) {
		List<String> paths = new ArrayList<String>();
		paths = getAllFilePaths(new File("C:\\JavaProducts"), paths);
		for (String path : paths) {
			System.out.println(path);
		}
	}

	private static List<String> getAllFilePaths(File filePath, List<String> filePaths) {
		File[] files = filePath.listFiles();
		if (files == null) {
			return filePaths;
		}
		for (File f : files) {
			if (f.isDirectory()) {
				filePaths.add(f.getPath());
				getAllFilePaths(f, filePaths);
			} else {
				filePaths.add(f.getPath());
			}
		}
		return filePaths;
	}
}