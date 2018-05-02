/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import pojo.Result;

/**
 * @author 分裂状态。
 *
 */
public class ResultSort {

	public static void main(String[] args) {
		List<Result> results = new ArrayList<Result>();
		List<Result> result = new ArrayList<Result>();

		List<String> rss = new ArrayList<String>();

		// results.add(new Result(1, "2002-01-02", "2002"));
		// results.add(new Result(3, "05-06", "2006"));
		// results.add(new Result(4, "07-14", "2007"));
		// results.add(new Result(5, "07-14", "2008"));
		// results.add(new Result(6, "07-14", "2009"));
		// results.add(new Result(7, "08-16", "2010"));
		// results.add(new Result(8, "09-05", "2011"));
		// results.add(new Result(9, "09-05", "2012"));
		// results.add(new Result(10, "09-05", "2013"));
		// results.add(new Result(11, "09-05", "2014"));

		results.add(new Result("2002-01-02"));
		results.add(new Result("2006-01-02"));
		results.add(new Result("2005-01-02"));
		results.add(new Result("2007-03-01"));
		results.add(new Result("2008-05-06"));
		results.add(new Result("2010-07-08"));
		results.add(new Result("2011-08-05"));
		Map<String, List<String>> map = new TreeMap<String, List<String>>();
		// for(Result rs : results){
		// String name = rs.getYear().substring("yyyy".length() + 1,
		// rs.getYear().length());
		// //System.out.println(rs.getYear().substring("yyyy".length() + 1,
		// rs.getYear().length()));
		// if(rs.getYear().substring("yyyy".length() + 1,
		// rs.getYear().length()).equals(name)){
		// rss.add(rs.getYear().substring(0,"yyyy".length()));
		// map.put(rs.getYear().substring("yyyy".length() + 1,
		// rs.getYear().length()), rss);
		// }
		//// else{
		//// rss.add(rs.getYear().substring(0,"yyyy".length()));
		//// }
		// //result.add(new Result(rs.getYear().substring("yyyy".length() + 1,
		// rs.getYear().length())));
		// }

		for (int i = 1; i < results.size(); i++) {
			if (results.get(i - 1).getYear().substring("yyyy".length() + 1, results.get(i).getYear().length()).equals(
					results.get(i).getYear().substring("yyyy".length() + 1, results.get(i).getYear().length()))) {
				rss.add(results.get(i - 1).getYear().substring(0, "yyyy".length()));
				map.put(results.get(i - 1).getYear().substring("yyyy".length() + 1, results.get(i).getYear().length()),
						rss);
			} else {
				rss.add(results.get(i - 1).getYear().substring(0, "yyyy".length()));
				map.put(results.get(i - 1).getYear().substring("yyyy".length() + 1, results.get(i).getYear().length()),
						rss);
			}
			// String name = rs.getYear().substring("yyyy".length() + 1,
			// rs.getYear().length());
			// System.out.println(rs.getYear().substring("yyyy".length() + 1,
			// rs.getYear().length()));
			// if(rs.getYear().substring("yyyy".length() + 1,
			// rs.getYear().length()).equals(name)){
			// rss.add(rs.getYear().substring(0,"yyyy".length()));
			//
			// }

			// map.put(results.get(i).getYear().substring("yyyy".length() + 1,
			// results.get(i).getYear().length()), rss);
			// else{
			// rss.add(rs.getYear().substring(0,"yyyy".length()));
			// }
			// result.add(new Result(rs.getYear().substring("yyyy".length() + 1,
			// rs.getYear().length())));
		}

		for (String str : map.keySet()) {
			System.out.println(str + "\t" + map.get(str));
			// for(String s : map.get(str)){
			// System.out.println(str);
			// }
		}

		// result.add(results.get(0));
		// for (int i = 1; i < results.size(); i++) {
		// if (results.get(i).getMon().equals(results.get(i - 1).getMon())) {
		// Result r = null;
		// for (int j = 0; j < result.size(); j++) {
		// if (result.get(j).getMon().equals(results.get(i).getMon())) {
		// r = result.get(j);
		// break;
		// }
		// }
		// if (r != null) {
		// r.setYear(r.getYear() + "," + results.get(i).getYear());
		// results.get(i - 1).setYear(r.getYear());
		// } else {
		// results.get(i - 1).setYear(results.get(i - 1).getYear() + "," +
		// results.get(i).getYear());
		// }
		// } else {
		// results.get(i).setId(result.size() + 1);
		// result.add(results.get(i));
		// }
		// }
		//
		//
		// for (int i = 0; i < result.size(); i++) {
		// System.out.println(result.get(i).getId() + "\t" +
		// result.get(i).getMon() + "\t" + result.get(i).getYear());
		// }
		

	}

}
