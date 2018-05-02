/**
 * 
 */
package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.CimissCount;

/**
 * @author 分裂状态。
 *
 */
public interface CimissCountDao extends GenericRepository<CimissCount, Integer>{

	@Query(value = "select * from CimissCount where isActive = 0 order by DateDate desc", nativeQuery = true)
	public List<CimissCount> listCimissCount();
	
	@Query(value = "select * from CimissCount order by DateDate desc", nativeQuery = true)
	public List<CimissCount> listCimissCountAll();
	
	@Modifying
	@Query("update CimissCount set isActive = ?1 where Id = ?2")
	public void updateCimissCount(boolean isActive,Integer id);
}
