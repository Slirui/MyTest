/**
 * 
 */
package test;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * @author 分裂状态。
 *
 */
public class FileJson {
	public static void main(String[] args) {
		String path = "E:\\Lyric_shui\\上海市公共气象服务\\WebContent\\doc\\2015年台风“灿鸿”\\_201710111548_总结材料.txt";
		//String path2 = "E:\\结果\\_201710111548_总结材料.txt";
		// String filepath = java.net.URLEncoder.encode(path,"UTF-8");
		File file = new File(path);
		if (file.exists()) {
			file.delete();
			System.out.println("删除成功");
		}
	}
}
