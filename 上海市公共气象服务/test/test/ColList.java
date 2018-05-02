/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.List;

import com.util.TestResult;

/**
 * @author 分裂状态。
 *
 */
public class ColList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Object> objList = new ArrayList<Object>();
		objList.add("2002-01-02");
		objList.add("2006-01-02");
		objList.add("2005-01-02");
		objList.add("2007-03-01");
		objList.add("2008-05-06");
		objList.add("2010-07-08");
		objList.add("2011-08-05");
		List<Object[]> lisobj = null;
		List<Object[]> objList2 = new ArrayList<Object[]>();
		TestResult tr = null;
		List<Object[]> objlist = new ArrayList<Object[]>();
		for (Object objs : objList) {
			Object[] obj = new Object[2];
			obj[0] = objs.toString().substring("yyyy".length() + 1, objs.toString().length());
			obj[1] = objs.toString().substring(0, "yyyy".length());
			objlist.add(obj);
		}
		for (int i = 0; i < objlist.size(); i++) {
			System.out.println(objlist.get(i)[0] + "\t" + objlist.get(i)[1]);
		}
		lisobj = new ArrayList<Object[]>();
		for (int i = 0; i < objlist.size(); i++) {
			Object[] obj = new Object[2];
			obj[0] = objlist.get(i)[0];
			obj[1] = objlist.get(i)[1];
			lisobj.add(obj);
		}
		tr = new TestResult();
		objList2 = tr.order(lisobj);
		
		
		for (int i = 0; i < objList2.size(); i++) {
			System.out.println(objList2.get(i)[0] + "\t" + objList2.get(i)[1]);
		}
	}

}
