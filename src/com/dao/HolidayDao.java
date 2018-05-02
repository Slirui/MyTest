/**
 * 
 */
package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.Holiday;

/**
 * @author 分裂状态。
 *
 */
public interface HolidayDao extends GenericRepository<Holiday, Integer> {

	@Query("select h.title from Holiday h")
	// 获取标题
	public List<String> getHolidayTitle();

	@Query("select h from Holiday h where h.title = ?")
	// 根据标题获取信息
	public List<Holiday> getHolidayByTitle(String title);

	@Query("select h from Holiday h where h.contents like ?")
	// 检索查询
	public List<Holiday> getHoliday(String content);

	@Modifying
	@Query("update Holiday set contents = ? where title = ?")
	public void updateContent(String contents, String title);

	@Query("select h from Holiday h where h.title = ?")
	// 根据标题获取信息
	public Holiday getHolidayInfoByTitle(String title);
}
