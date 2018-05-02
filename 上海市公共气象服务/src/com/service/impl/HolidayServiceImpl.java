/**
 * 
 */
package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.HolidayDao;
import com.entity.Holiday;
import com.service.HolidayService;

/**
 * @author 分裂状态。
 *
 */
@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	private HolidayDao holidayDao;

	// 获取标题
	public List<String> getHolidayTitle() {
		return holidayDao.getHolidayTitle();
	}

	// 根据标题获取信息
	public List<Holiday> getHolidayByTitle(String title) {
		return holidayDao.getHolidayByTitle(title);
	}

	// 检索查询
	public List<Holiday> getHoliday(String content) {
		return holidayDao.getHoliday(content);
	}

	public void updateContent(String contents, String title) {
		holidayDao.updateContent(contents, title);
	}

	public Holiday getHolidayInfoByTitle(String title) {
		return holidayDao.getHolidayInfoByTitle(title);
	}
}
