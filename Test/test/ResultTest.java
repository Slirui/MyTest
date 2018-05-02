/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author 分裂状态。
 *
 */
public class ResultTest {
	public static void main(String[] args) {
		List<Object[]> list = new ArrayList<Object[]>();
		List<Object[]> newlist = new ArrayList<Object[]>();
		list.add(new Object[] { "1", "1995" });
		list.add(new Object[] { "1", "1996" });// 乱的顺序 我知道 你排序了
		list.add(new Object[] { "2", "1997" });
		list.add(new Object[] { "4", "1998" });
		list.add(new Object[] { "3", "1999" });
		list.add(new Object[] { "3", "2000" });
		list.add(new Object[] { "5", "2001" });
		ResultTest t = new ResultTest();
		newlist = t.order(list);
		for (int i = 0; i < newlist.size(); i++) {
			System.out.println(newlist.get(i)[0] + "\t" + newlist.get(i)[1]);
		}
		for(int i = 0; i < newlist.size(); i++){
			System.out.println(newlist.get(i)[0] + "\t" + newlist.get(i)[1]);
		}
	}

	public List<Object[]> order(List<Object[]> list) {
		list.sort(new Comparator<Object[]>() {
			public int compare(Object[] o1, Object[] o2) {
				if ((Integer.parseInt(o1[0].toString())) > (Integer.parseInt(o2[0].toString()))) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		List<Object[]> newlist = new ArrayList<Object[]>();
		Object[] obj = new Object[2]; // 这个的
		obj[0] = list.get(0)[0].toString();// 第一次不比较，
		obj[1] = list.get(0)[1].toString();// 存到 obj 和第二次比较
		for (int i = 1; i < list.size(); i++) {// 这是二次 第一次在哪
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
