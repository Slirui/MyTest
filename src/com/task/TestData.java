/**
 * 
 */
package com.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.ListSort;
import com.service.AnotherYearService;

/**
 * @author 分裂状态。
 *
 */
@Component("TestData")
public class TestData {

	@Autowired
	private AnotherYearService service;

	//@Scheduled(cron = "0/5 * * * * ?")
	public void year() {
		System.out.println("====================================");
		String beginYear = "1874";
		String endYear = "2017";
		List<Object[]> obj = service.getAutumnMaxLength(beginYear, endYear);
		List<ListSort> listinfo = new ArrayList<ListSort>();
		// List<Object[]> obj = col1;
		for (int z = 0; z < obj.size(); z++) {
			ListSort li = new ListSort();
			li.setLength(obj.get(z)[0].toString());
			li.setYear(obj.get(z)[1].toString());
			if (listinfo.contains(obj.get(z))) {
				ListSort info = listinfo.get(listinfo.size() - 1);
				info.setYear(info.getYear() + "," + obj.get(z)[1]);
			} else {
				listinfo.add(li);
			}
		}
		for (ListSort as : listinfo) {
			System.out.println(as.getLength() + "\t" + as.getYear());
		}
	}
}
