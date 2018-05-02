/**
 * 
 */
package com.service;

import java.util.List;

import com.entity.Holiday;

/**
 * @author 分裂状态。
 *
 */
public interface HolidayService {

	// 获取标题
	public List<String> getHolidayTitle();

	// 根据标题获取信息
	public List<Holiday> getHolidayByTitle(String title);

	// 检索查询
	public List<Holiday> getHoliday(String content);

	public void updateContent(String contents, String title);
	
	public Holiday getHolidayInfoByTitle(String title);

}
