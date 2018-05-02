/**
 * 
 */
package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSONObject;
import com.entity.FileOperate;
import com.entity.Warn;

/**
 * @author 分裂状态。
 *
 */
public class FileParse {
	public FileParse() {
	}

	public FileParse(HttpServletRequest request) {
		getJsonPngName(request);
	}

	public static Map map = null;

	public static Map map2 = null;

	/**
	 * 解析JSON进行整改
	 * 
	 * @param request
	 */
	public static void getJsonPngName(HttpServletRequest request) {
		InputStream is = null;
		String path = request.getServletContext().getRealPath("/json/imagedata.json");
		File myFile = new File(path);
		String json = "";
		try {
			is = new FileInputStream(myFile);
			byte[] data = new byte[is.available()];
			json = new String(data);
			IOUtils.read(is, data);
			json = new String(data, "UTF-8");
			json = json.substring(1, json.length());
			json = json.substring(0, json.length() - 1);
			json = json.replace("\",", "@");
			String[] arr = json.split(",");
			map = new HashMap();
			map2 = new HashMap();
			for (String str : arr) {
				str = str.replace("@", "\",");
				map.put(map.size() + "", JSONObject.parse(str));
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 1、快报 2、专报 3、服务总结
	 * 
	 * @param file
	 * @return
	 */
	public static FileOperate parse(File file) {
		try {
			FileOperate fo = new FileOperate();
			String filename = file.getName();
			String reg = "20[0-9]{10,12}";
			Date date = null;
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(filename);
			String fqdnId = "";
			if (matcher.find()) {
				fqdnId = matcher.group();
				date = new SimpleDateFormat("yyyyMMddHHmm").parse(fqdnId);
			}
			int first = filename.indexOf("_");
			filename = filename.substring(first + 1);
			filename = filename.replaceFirst("_", "");// .substring(reg.length());
			filename = filename.substring(fqdnId.length());
			int last = filename.lastIndexOf(".");
			filename = filename.substring(0, last);
			fo.setFileName(file.getName());
			fo.setTitle(filename);
			fo.setDate(date);
			return fo;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 针对预警显示流程图
	 * 
	 * @param file
	 * @return
	 */
	public static Warn warnparse(File file) {
		try {
			Warn warn = new Warn();
			String filename = file.getName();
			String reg = "20[0-9]{10,12}";
			Date date = null;
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(filename);
			String fqdnId = "";
			if (matcher.find()) {
				fqdnId = matcher.group();
				date = new SimpleDateFormat("yyyyMMddHHmm").parse(fqdnId);
			}
			int first = filename.indexOf("_");
			filename = filename.substring(first + 1);
			filename = filename.replaceFirst("_", "");// .substring(reg.length());
			filename = filename.substring(fqdnId.length());
			int last = filename.lastIndexOf(".");
			filename = filename.substring(0, last);
			String pngname = "";
			for (Object str : map.keySet()) {
				if (file.getName().contains(((JSONObject) map.get(str)).get("name").toString())) {
					pngname = ((JSONObject) map.get(str)).get("icon").toString();
					break;
				}
			}
			warn.setWarnname(file.getName());
			warn.setWarntitle(filename);
			warn.setWarndate(date);
			warn.setWarnicon(pngname);
			return warn;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Map嵌套Map 一、处理预警数据 以流程图显示出来
	 * 
	 * @param map
	 * @param file
	 * @return
	 */
	public static Map<String, Map<String, Warn>> getType(Map map, File file) {
		String filename = file.getName();
		String reg = "20[0-9]{10,12}";
		Date date = null;
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(filename);
		String fqdnId = "";
		if (matcher.find()) {
			fqdnId = matcher.group();
		}
		int first = filename.indexOf("_");
		filename = filename.substring(first + 1);
		filename = filename.replaceFirst("_", "");// .substring(reg.length());
		filename = filename.substring(fqdnId.length());
		int last = filename.lastIndexOf(".");
		filename = filename.substring(0, last);
		String type = "";
		type = filename.replaceAll("预警信号", "");
		String filenamereg = "[\u4e00-\u9fa5]色";
		type = type.replaceAll(filenamereg, "");
		if (type.contains("解除")) {
			type = type.replaceAll("解除", "");
		}
		if (map.get(type) == null) {
			Map map2 = new TreeMap();
			map2.put(fqdnId, warnparse(file));
			map.put(type, map2);
		} else {
			Map map2 = (Map) map.get(type);
			map2.put(fqdnId, warnparse(file));
			map.put(type, map2);
		}
		return map;
	}

	/**
	 * Map<String,ArrayList<T>>方法 1、此方法比较好理解 ，可以实现排序 2、操作起来方便 二、处理预警数据 以流程图显示出来
	 * 
	 * @param map
	 * @param file
	 * @return
	 */
	public static void getType2(Map map, File file) {
		String filename = file.getName();
		String reg = "20[0-9]{10,12}";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(filename);
		String fqdnId = "";
		if (matcher.find()) {
			fqdnId = matcher.group();
		}
		int first = filename.indexOf("_");
		filename = filename.substring(first + 1);
		filename = filename.replaceFirst("_", "");// .substring(reg.length());
		filename = filename.substring(fqdnId.length());
		int last = filename.lastIndexOf(".");
		filename = filename.substring(0, last);
		String type = "";
		type = filename.replaceAll("预警信号", "");
		String filenamereg = "[\u4e00-\u9fa5]色";
		type = type.replaceAll(filenamereg, "");
		if (type.contains("解除")) {
			type = type.replaceAll("解除", "");
		}
		if (map.get(type) == null) {
			List<Warn> list = new ArrayList<Warn>();
			Warn warn = warnparse(file);
			warn.setWarntitle("发布");
			list.add(warn);
			map.put(type, list);
		} else {
			List<Warn> list = (List) map.get(type);
			Warn warn = warnparse(file);
			if (file.getName().contains("解除")) {
				warn.setWarntitle("解除");
				List<Warn> list2 = new ArrayList<Warn>();
				list2.addAll(list);
				list2.add(warn);
				WarnComparator warncomp = new WarnComparator();
				Collections.sort(list2, warncomp);
				map.put(type + map.size(), list2);
				map.remove(type);
			} else {
				warn.setWarntitle("更新");
				list.add(warn);
				WarnComparator warncomp = new WarnComparator();
				Collections.sort(list, warncomp);
			}
		}

	}

	public static void main(String[] args) throws Exception {
		// new FileParse().getJsonPngName();
		String filename = "yj_201507100912_解除台风黄色预警信号.txt";
		String reg = "20[0-9]{10,12}";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(filename);
		String fqdnId = "";
		if (matcher.find()) {
			fqdnId = matcher.group();
			// date = new SimpleDateFormat("yyyyMMddHHmm").parse(fqdnId);
		}
		int first = filename.indexOf("_");
		filename = filename.substring(first + 1);
		filename = filename.replaceFirst("_", "");// .substring(reg.length());
		filename = filename.substring(fqdnId.length());
		int last = filename.lastIndexOf(".");
		filename = filename.substring(0, last);// kb_20150711230000_上海市解除暴雨黄色预警信号.doc
		System.out.println("名称：" + filename + "\t时间：" + fqdnId);
		filename = filename.replaceAll("预警信号", "");
		String filenamereg = "[\u4e00-\u9fa5]色";
		filename = filename.replaceAll(filenamereg, "");
		if (filename.contains("解除")) {
			filename = filename.replaceAll("解除", "");
		}
		System.out.println(filename);

	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	// 之后整改
	public static FileOperate recOther(File file, HttpServletRequest request, HttpSession session) {
		try {
			FileOperate fo = new FileOperate();
			String filename = file.getName();
			String reg = "20[0-9]{10,12}";
			Date date = null;
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(filename);
			String fqdnId = "";
			if (matcher.find()) {
				fqdnId = matcher.group();
				date = new SimpleDateFormat("yyyyMMddHHmm").parse(fqdnId);
			} else {
				fqdnId = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
				date = new SimpleDateFormat("yyyyMMddHHmm").parse(fqdnId);
			}
			int first = filename.indexOf("_");
			if (first != -1) {
				filename = filename.substring(first + 1);
				filename = filename.replaceFirst("_", "");
				filename = filename.substring(fqdnId.length());
				int last = filename.lastIndexOf(".");
				filename = filename.substring(0, last);
			} else {
				filename = "_" + fqdnId + "_" + filename;
				String root = "";
				String node = session.getAttribute("node").toString();
				String lj = "";
				String path = request.getServletContext().getRealPath("/");
				if (session.getAttribute("root") != null && !session.getAttribute("root").equals("")) {
					root = session.getAttribute("root").toString();
					lj = path + "doc/" + root + "/" + node + "/" + filename;
				} else {
					lj = path + "doc/" + node + "/" + filename;
				}
				File fe = new File(lj);
				FileUtils.moveFile(file, fe);
				file = fe;
				int fefirst = filename.indexOf("_");
				if (fefirst != -1) {
					filename = filename.substring(fefirst + 1);
				}
				filename = filename.replaceFirst("_", "");// .substring(reg.length());
				filename = filename.substring(fqdnId.length());
				int last = filename.lastIndexOf(".");
				filename = filename.substring(0, last);
			}
			fo.setFileName(file.getName());
			fo.setTitle(filename);
			fo.setDate(date);
			return fo;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用 Map按key进行排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, ArrayList<Warn>> sortMapByKey(Map<String, ArrayList<Warn>> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, ArrayList<Warn>> sortMap = new TreeMap<String, ArrayList<Warn>>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}

	public static Map getMap2() {
		return map2;
	}

	public static void setMap2(Map map2) {
		FileParse.map2 = map2;
	}

}
