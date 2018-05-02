/**
 * 
 */
package com.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 分裂状态。
 *
 */
public class TestResult {

	public static void main(String[] args) {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] { "35", "2014" });
		list.add(new Object[] { "35", "2015" });
		list.add(new Object[] { "36", "2016" });
		list.add(new Object[] { "37", "2017" });
		List<Object[]> result = new TestResult().order(list);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i)[0] + "\t" + result.get(i)[1]);
		}
	}

	public List<Object[]> order(List<Object[]> list) {
		List<Object[]> newlist = new ArrayList<Object[]>();
		Object[] obj = new Object[2];
		obj[0] = list.get(0)[0].toString();
		obj[1] = list.get(0)[1].toString();
		for (int i = 1; i < list.size(); i++) {
			if (obj[0].toString().equals(list.get(i)[0].toString())) {
				obj[1] = obj[1].toString() + "," + list.get(i)[1].toString();
			} else {
				newlist.add(obj);
				obj = new Object[2];
				obj[0] = list.get(i)[0].toString();
				obj[1] = list.get(i)[1].toString();
			}
		}
		newlist.add(obj);
		return newlist;
	}
}
