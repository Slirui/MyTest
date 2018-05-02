/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.CommonAnotherYear;

import pojo.InfoList;

/**
 * 
 * @author 分裂状态。
 *
 */
public class ListSortOrMap {

	public static void main(String[] args) {
		List<InfoList> list = new ArrayList<InfoList>();
		InfoList il1 = new InfoList();
		InfoList il2 = new InfoList();
		InfoList il3 = new InfoList();
		InfoList il4 = new InfoList();
		InfoList il5 = new InfoList();
		InfoList il6 = new InfoList();
		InfoList il7 = new InfoList();
		InfoList il8 = new InfoList();
		il1.setCol1ListMax("1");
		il1.setCol1ListYear("1998");
		il2.setCol1ListMax("2");
		il2.setCol1ListYear("1999");
		il3.setCol1ListMax("2");
		il3.setCol1ListYear("2016");
		il4.setCol1ListMax("3");
		il4.setCol1ListYear("2000");
		il5.setCol1ListMax("4");
		il5.setCol1ListYear("2010");
		il6.setCol1ListMax("4");
		il6.setCol1ListYear("2011");
		il7.setCol1ListMax("3");
		il7.setCol1ListYear("2002");
		il8.setCol1ListMax("4");
		il8.setCol1ListYear("2017");
		list.add(il1);
		list.add(il2);
		list.add(il3);
		list.add(il4);
		list.add(il5);
		list.add(il6);
		list.add(il7);
		list.add(il8);
		// 调用排序方法
		Collections.sort(list, new Comparator<InfoList>() {
			@Override
			public int compare(InfoList o1, InfoList o2) {
				if (Integer.parseInt(o1.getCol1ListMax()) < Integer.parseInt(o2.getCol1ListMax())) {
					return 1;
				} else if (Integer.parseInt(o1.getCol1ListMax()) == Integer.parseInt(o2.getCol1ListMax())) {
					return 0;
				} else {
					return -1;
				}
			}
		});
		Map<String, String> map = new HashMap<String, String>();
		List<InfoList> newlist = new ArrayList<InfoList>();
		InfoList old = new InfoList();
		old = list.get(0);
		newlist.add(old);
		map.put(newlist.get(0).getCol1ListMax(), newlist.get(0).getCol1ListYear());
		for (int i = 1; i < list.size(); i++) {
			InfoList newil = new InfoList();
			newil = newlist.get(i - 1);
			if (newil.getCol1ListMax().equals(list.get(i).getCol1ListMax())) {
				newil.setCol1ListYear(newil.getCol1ListYear() + "," + list.get(i).getCol1ListYear());
				newlist.add(newil);
				map.put(newil.getCol1ListMax(), newil.getCol1ListYear());
			} else {
				newlist.add(list.get(i));
				map.put(newlist.get(i).getCol1ListMax(), newlist.get(i).getCol1ListYear());
			}
		}
		for (String d : map.keySet()) {
			System.out.println("键=====：" + d + "\t值=====：" + map.get(d));
		}
	}
}
