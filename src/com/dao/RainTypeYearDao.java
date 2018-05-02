/**
 * 
 */
package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.entity.Precipitation;

/**
 * @author 分裂状态。
 *
 */
public interface RainTypeYearDao extends GenericRepository<Precipitation, Integer> {

	
	// 获取年
	@Query(value="select distinct Year(DateDate) from Precipitation order by Year(DateDate) asc")
	public List<String> getYear();
}
