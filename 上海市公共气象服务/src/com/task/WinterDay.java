/**
 * 
 */
package com.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.entity.AnotherYear;
import com.service.AnotherYearService;

/**
 * @author 分裂状态。
 *
 */
@Component("WinterDay")
public class WinterDay {

	@Autowired
	private AnotherYearService anotherYearService;

	@Scheduled(cron = "0 0 0/24 * * ?")
	// @Scheduled(cron = "0/2 * * * * ?")
	public void winterDays() {
		List<AnotherYear> anotherYears = anotherYearService.getAll();
		Map<Integer, AnotherYear> map = new HashMap<Integer, AnotherYear>();
		for (AnotherYear ano : anotherYears) {
			map.put(ano.getSpringDate().getYear() + 1900, ano);
		}
		int year = new Date().getYear() + 1900;
		for (int i = year; i >= 1875; i--) {
			try {
				if (map.containsKey(i) && map.get(i).getSpringDate() != null) {
					long diff = (map.get(i).getSpringDate().getTime() - map.get(i - 1).getWinterDate().getTime())
							/ (1000 * 60 * 60 * 24);
					int days = (int) diff;
					anotherYearService.updateInfo(days, map.get(i - 1).getId());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
