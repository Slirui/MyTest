/**
 * 
 */
package test;

import java.io.File;
import java.io.IOException;

/**
 * @author 分裂状态。
 *
 */
public class Test {

	public static void main(String[] args) throws IOException {
		/*Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int mon = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		System.out.println(year);
		System.out.println(mon);
		System.out.println(day);
		Date date = new Date();
		date.setDate(date.getDate() - 1);
		System.out.println(date);*/
		// add();
		// update();
		// delete("E:\\修改\\1");
//		String str = "123.doc";
//		str = str.substring(0, str.indexOf("."));
//		System.out.println(str);
	}

	public static void add() throws IOException {
		File a = new File("E:\\添加");
		if (!a.exists()) {
			a.mkdirs();
		}
	}

	public static void update() {
		File a = new File("E:\\添加");
		System.out.println(a.renameTo(new File("E:\\修改")));
	}

	public static void delete(String path) {
		File file = new File(path);
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File subFile : files) {
					if (subFile.isDirectory()) {
						delete(subFile.getPath());
					} else {
						subFile.delete();
					}
				}
			}
			file.delete();
		}
	}

}
