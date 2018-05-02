/**
 * 
 */
package com.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.entity.Precipitation;
import com.entity.Result;
import com.service.AnotherYearService;
import com.service.ResultService;

/**
 * @author 分裂状态。
 *
 */
@Component("ResultDay")
public class ResultDay {

	@Autowired
	private AnotherYearService service;

	@Autowired
	private ResultService resultService;

	// @Scheduled(cron = "0 0 0/24 * * ?")
	@Scheduled(cron = "0 0 2 * * ?")
	// @Scheduled(cron = "0/5 * * * * ?")
	public void year() {
		int year = new Date().getYear() + 1900;
		resultService.deleteResult(year);
		for (int i = year; i >= 1951; i--) {
			List<Precipitation> list = service.getInfo(i);
			for (int t : new int[] { 35, 36, 37, 38, 39, 40 }) {
				int count = 0;
				int max = 0;
				for (Precipitation pre : list) {
					if (pre.getTemMax() >= t) {
						count++;
					} else {
						count = 0;
					}
					max = Math.max(max, count);
				}
				boolean isOK = false;
				if (i == year) {
					isOK = true;
				} else {
					List<Result> results = resultService.getResults(i, t);
					if (results.size() == 0) {
						isOK = true;
					}
				}
				if (isOK) {
					Result result = new Result();
					result.setDateYear(i);
					result.setDayCount(max);
					result.setTem(t);
					resultService.save(result);
				}
			}
		}
	}
}
