/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.List;

import pojo.Test;

/**
 * @author 分裂状态。
 *
 */
public class ListTest {

	public static void main(String[] args) {
		List<Test> list = new ArrayList<Test>();
		list.add(new Test(100));
		list.add(new Test(90));
		list.add(new Test(90));
		list.add(new Test(80));
		list.add(new Test(80));
		list.add(new Test(60));

		Integer a = null, b = 0,c = 1;
		for (Test test : list) {
			if (test.getScore() == a) {
				test.setRankNo(b);
			} else {
				a = test.getScore();
				b += 1;
				test.setRankNo(b);
			}
			/*if (test.getScore() == a) {
				c += 1;
				test.setRankNo(b);
			} else {
				a = test.getScore();
				b += c;
				c = 1;
				test.setRankNo(b);
			}*/
		}

		for (Test test : list) {
			System.out.println(test.getRankNo() + "\t" + test.getScore());
		}
	}
}
