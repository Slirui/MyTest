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
public class TestUtil {

	class Num {
		private Integer num;
		private String str;

		public Integer getNum() {
			return num;
		}

		public void setNum(Integer num) {
			this.num = num;
		}

		public String getStr() {
			return str;
		}

		public void setStr(String str) {
			this.str = str;
		}

		@Override
		public boolean equals(Object arg0) {
			return ((Num) arg0).num == this.num;
		}

	}

	public static void main(String[] args) {
		new TestUtil().Test();
	}

	public void Test() {
		/*Num na1 = new Num();
		Num na2 = new Num();
		Num na3 = new Num();
		List<Object[]> obj = new ArrayList<Object[]>();
		Object[] str1 = { na1.getNum(), na1.getStr() };
		Object[] str2 = { na2.getNum(), na2.getStr() };
		Object[] str3 = { na3.getNum(), na3.getStr() };
		str1[0] = "85";
		str1[1] = "2012";
		str2[0] = "85";
		str2[1] = "2016";
		str3[0] = "84";
		str3[1] = "2017";
		obj.add(str1);
		obj.add(str2);
		obj.add(str3);
		List<Num> list = new ArrayList<Num>();
		for (int i = 0; i < obj.size(); i++) {
			Num n = new Num();
			n.setNum(obj.get(i)[0].toString());
			n.setStr(obj.get(i)[1].toString());
			if (list.contains(n)) {
				Num m = list.get(list.size() - 1);
				m.setStr(m.getStr() + "," + obj.get(i)[1].toString());
			} else {
				list.add(n);
			}//这个方法 没有什么错的啊  这样代码少
		}*/
//我的适用于所有两列值得操作，不是对象，
		

		int[] num = { 10, 10, 9, 7, 6, 5, 4 };
		List<Num> list = new ArrayList<Num>();
		for (int i = 0; i < num.length; i++) {
			Num n = new Num();
			n.setNum(num[i]);
			n.setStr(i + "");
			if (list.contains(n)) {
				Num m = list.get(list.size() - 1);
				m.setStr(m.getStr() + "," + i);
			} else {
				list.add(n);
			}
		}

		for (Num s : list) {
			System.out.println(s.getNum() + "\t" + s.getStr());
		}
	}
}
