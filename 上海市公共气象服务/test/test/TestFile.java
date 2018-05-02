/**
 * 
 */
package test;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.poifs.storage.HeaderBlockWriter;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 分裂状态。
 *
 */
public class TestFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// String fileName = '台风蓝色预警信号';
		// String type = fileName.replaceAll('预警信号', '');
		// String filenamereg = '[\u4e00-\u9fa5]色';
		// type = type.replaceAll(filenamereg, '');
		//
		// String filename = 'yj_201409211700_台风蓝色预警信号.txt';
		// int first = filename.indexOf('_');
		// filename = filename.substring(first + 1);
		// filename = filename.replaceFirst('_', '');//
		// .substring(reg.length());
		// filename = filename.substring(12);
		// int last = filename.lastIndexOf('.');
		// filename = filename.substring(0, last);
		// System.out.println(filename);
		String json = "[{'name': '臭氧黄色','icon': 'hff0736-47.png'},{'name': '臭氧橙色','icon': 'hff0736-48.png'}]";
		json = json.substring(1, json.length());
		json = json.substring(0, json.length() - 1);
		json = json.replace("\',", "@");
		System.out.println(json);
		String[] arr = json.split(",");
		Map map = new HashMap();
		for(String str : arr){
			str = str.replace("@", "\',");
			System.out.println(str);
			map.put(map.size() + "", JSONObject.parse(str));
			//map.put(map.size() + "", JSONObject.parse(str));
		}
		System.out.println(map);
		for (Object str : map.keySet()) {
			//String result = ((JSONObject) map.get(str)).get("name").toString();
			String result = ((JSONObject) map.get(str)).toString();
			System.out.println(str);
			System.out.println(result);
		}
	}

}
