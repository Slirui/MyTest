/**
 * 
 */
package test;

import java.io.File;

/**
 * @author 分裂状态。
 *
 */
public class FileTest2 {

	public static void main(String[] args) {
		// File file = new File("E:\\Lyric_shui\\上海市公共气象服务\\WebContent\\doc");
		// File[] files = file.listFiles();
		// parse(files);
		//int[] num = new int[] { 1, 2, 3, 4 };
		
		for (int i : new int[] { 1, 2, 3, 4 }) {
			int count = 0;
			int maxCount = 0;
			if (i >= 2) {
				count++;
			} else {
				maxCount = Math.max(maxCount, count);
				count = 0;
			}
			System.out.println(maxCount);
		}
		/*
		 * int count = 1; int maxCount = 0;
		 * System.out.println(Math.max(maxCount, count));
		 */

	}

	public static int count = 0;

	public static void parse(File[] files) {
		if (files.length == 0) {
			// count--;
			return;
		} else {
			for (File f : files) {
				if (f.isDirectory()) {
					/*
					 * count++; for (int i = 1; i <= FileTest2.count; i++) {
					 * System.out.print("\t"); }
					 */
					System.out.println(count);
					System.out.println(f.getName());
					File[] files2 = f.listFiles();
					parse(files2);
				}
			}
			// count--;
		}
	}
	
}
