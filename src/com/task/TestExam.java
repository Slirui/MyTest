/**
 * 
 */
package com.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.Test;
import com.service.TestService;

/**
 * @author 分裂状态。
 *
 */
@Component("Test")
public class TestExam {

	@Autowired
	private TestService service;

	// @Scheduled(cron = "0/10 * * * * ?")
	public void TestInfo() {
		List<Object[]> counts = service.getTemDayCounts();
		List<Object[]> results = service.getTemDayCount();
		Map<Integer, Test> map = new HashMap<Integer, Test>();
		for (int i = 0; i < results.size(); i++) {
			Test ts = new Test();
			int year = Integer.parseInt(results.get(i)[1].toString());
			ts.setMaxYear(year);
			if (!map.containsKey(year)) {
				map.put(year, ts);
			}
			int num = Integer.parseInt(counts.get(i)[0].toString());
			ts.setMaxSumDay(num);
			int month = Integer.parseInt(results.get(i)[2].toString());
			int value = Integer.parseInt(results.get(i)[0].toString());
			map.get(year).setMonthValue(month, value);
		}
	}

}
